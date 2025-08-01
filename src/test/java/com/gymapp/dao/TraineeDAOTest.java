package com.gymapp.dao;

import com.gymapp.config.AppConfig;
import com.gymapp.model.Trainee;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TraineeDAOTest {

    private static AnnotationConfigApplicationContext context;
    private static TraineeDAO traineeDAO;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        traineeDAO = context.getBean(TraineeDAO.class);
    }

    @AfterAll
    static void cleanup() {
        context.close();
    }

    @Test
    @Order(1)
    void testFindAll() {
        List<Trainee> trainees = traineeDAO.findAll();
        assertFalse(trainees.isEmpty(), "Trainees list should not be empty");
    }

    @Test
    @Order(2)
    void testCreateAndFindById() {
        Trainee trainee = new Trainee();
        trainee.setId("t100");
        trainee.setFirstName("Test");
        trainee.setLastName("User");
        trainee.setUsername("test.user");
        trainee.setPassword("pass123");
        trainee.setActive(true);
        trainee.setDateOfBirth(LocalDate.of(2000, 1, 1));
        trainee.setAddress("Some Address");
        traineeDAO.create(trainee);

        Trainee found = traineeDAO.findById("t100");
        assertNotNull(found);
        assertEquals("Test", found.getFirstName());
    }

    @Test
    @Order(3)
    void testUpdate() {
        Trainee trainee = traineeDAO.findById("t100");
        trainee.setAddress("Updated Address");
        traineeDAO.update(trainee);

        Trainee updated = traineeDAO.findById("t100");
        assertEquals("Updated Address", updated.getAddress());
    }

    @Test
    @Order(4)
    void testDelete() {
        traineeDAO.delete("t100");
        assertNull(traineeDAO.findById("t100"));
    }
}