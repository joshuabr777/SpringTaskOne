package com.gymapp.facade;

import com.gymapp.config.AppConfig;
import com.gymapp.model.Trainer;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainerFacadeTest {

    private static AnnotationConfigApplicationContext context;
    private static TrainerFacade trainerFacade;

    @BeforeAll
    static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainerFacade = context.getBean(TrainerFacade.class);
    }

    @AfterAll
    static void teardown() {
        context.close();
    }

    @Test
    @Order(1)
    void testCreateGeneratesUsernameAndPassword() {
        Trainer trainer = new Trainer();
        trainer.setId("trf100");
        trainer.setFirstName("Diana");
        trainer.setLastName("Prince");
        trainer.setSpecialization("Yoga");

        Trainer saved = trainerFacade.createTrainer(trainer);

        assertNotNull(saved.getUsername(), "Username should be generated");
        assertEquals(10, saved.getPassword().length(), "Password should be 10 characters");
    }

    @Test
    @Order(2)
    void testUpdate() {
        Trainer trainer = new Trainer();
        trainer.setId("trf101");
        trainer.setFirstName("Eve");
        trainer.setLastName("Adams");
        trainer.setSpecialization("Pilates");
        trainerFacade.createTrainer(trainer);

        trainer.setSpecialization("Updated");
        trainerFacade.updateTrainer(trainer);

        assertEquals("Updated", trainerFacade.getTrainer("trf101").getSpecialization());
    }

    @Test
    @Order(3)
    void testGetAllTrainers() {
        assertFalse(trainerFacade.getAllTrainers().isEmpty(), "Should return trainers");
    }
}