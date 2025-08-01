package com.gymapp.facade;

import com.gymapp.config.AppConfig;
import com.gymapp.model.*;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainingFacadeTest {

    private static AnnotationConfigApplicationContext context;
    private static TrainingFacade trainingFacade;
    private static TraineeFacade traineeFacade;
    private static TrainerFacade trainerFacade;

    @BeforeAll
    static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainingFacade = context.getBean(TrainingFacade.class);
        traineeFacade = context.getBean(TraineeFacade.class);
        trainerFacade = context.getBean(TrainerFacade.class);
    }

    @AfterAll
    static void teardown() {
        context.close();
    }

    @Test
    @Order(1)
    void testCreateTraining() {
        Trainee trainee = new Trainee();
        trainee.setId("tf200");
        trainee.setFirstName("Greg");
        trainee.setLastName("Harris");
        trainee.setActive(true);
        trainee.setDateOfBirth(LocalDate.of(1994, 5, 5));
        trainee.setAddress("Addr");

        traineeFacade.createTrainee(trainee);

        Trainer trainer = new Trainer();
        trainer.setId("trf200");
        trainer.setFirstName("Hank");
        trainer.setLastName("Moore");
        trainer.setSpecialization("Cardio");
        trainerFacade.createTrainer(trainer);

        Training training = new Training("trainf200", trainee, trainer,
                "Morning Cardio", new TrainingType("Cardio"), LocalDate.now(), 45);

        Training saved = trainingFacade.createTraining(training);
        assertNotNull(saved, "Training should be created");
        assertEquals("Morning Cardio", saved.getTrainingName());
    }

    @Test
    @Order(2)
    void testGetAllTrainings() {
        assertFalse(trainingFacade.getAllTrainings().isEmpty(), "Should return trainings");
    }
}