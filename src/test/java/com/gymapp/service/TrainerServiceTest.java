package com.gymapp.service;

import com.gymapp.config.AppConfig;
import com.gymapp.model.Trainer;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainerServiceTest {

    private static AnnotationConfigApplicationContext context;
    private static TrainerService trainerService;

    @BeforeAll
    static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainerService = context.getBean(TrainerService.class);
    }

    @AfterAll
    static void teardown() {
        context.close();
    }

    @Test
    @Order(1)
    void testCreateGeneratesUsernameAndPassword() {
        Trainer trainer = new Trainer();
        trainer.setId("tr200");
        trainer.setFirstName("Diana");
        trainer.setLastName("Prince");
        trainer.setSpecialization("Yoga");

        Trainer saved = trainerService.createTrainer(trainer);

        assertNotNull(saved.getUsername(), "Username should be generated");
        assertTrue(saved.getPassword().length() == 10, "Password should be 10 characters");
    }

    @Test
    @Order(2)
    void testUpdate() {
        Trainer trainer = new Trainer();
        trainer.setId("tr201");
        trainer.setFirstName("Eve");
        trainer.setLastName("Adams");
        trainer.setSpecialization("Pilates");
        trainerService.createTrainer(trainer);

        trainer.setSpecialization("Updated Spec");
        trainerService.updateTrainer(trainer);

        Trainer updated = trainerService.getTrainerById("tr201");
        assertEquals("Updated Spec", updated.getSpecialization());
    }
}