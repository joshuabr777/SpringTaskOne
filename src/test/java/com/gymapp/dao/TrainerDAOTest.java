package com.gymapp.dao;

import com.gymapp.config.AppConfig;
import com.gymapp.model.Trainer;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainerDAOTest {

    private static AnnotationConfigApplicationContext context;
    private static TrainerDAO trainerDAO;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainerDAO = context.getBean(TrainerDAO.class);
    }

    @AfterAll
    static void cleanup() {
        context.close();
    }

    @Test
    @Order(1)
    void testFindAll() {
        List<Trainer> trainers = trainerDAO.findAll();
        assertFalse(trainers.isEmpty(), "Trainers list should not be empty");
    }

    @Test
    @Order(2)
    void testCreateAndFindById() {
        Trainer trainer = new Trainer();
        trainer.setId("tr100");
        trainer.setFirstName("New");
        trainer.setLastName("Trainer");
        trainer.setUsername("new.trainer");
        trainer.setPassword("abc123");
        trainer.setActive(true);
        trainer.setSpecialization("Yoga");
        trainerDAO.create(trainer);

        Trainer found = trainerDAO.findById("tr100");
        assertNotNull(found);
        assertEquals("New", found.getFirstName());
    }

    @Test
    @Order(3)
    void testUpdate() {
        Trainer trainer = trainerDAO.findById("tr100");
        trainer.setSpecialization("Pilates");
        trainerDAO.update(trainer);

        Trainer updated = trainerDAO.findById("tr100");
        assertEquals("Pilates", updated.getSpecialization());
    }
}