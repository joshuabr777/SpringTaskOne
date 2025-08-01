package com.gymapp.service;

import com.gymapp.config.AppConfig;
import com.gymapp.model.*;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainingServiceTest {

    private static AnnotationConfigApplicationContext context;
    private static TrainingService trainingService;
    private static TraineeService traineeService;
    private static TrainerService trainerService;

    @BeforeAll
    static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainingService = context.getBean(TrainingService.class);
        traineeService = context.getBean(TraineeService.class);
        trainerService = context.getBean(TrainerService.class);
    }

    @AfterAll
    static void teardown() {
        context.close();
    }

    @Test
    @Order(1)
    void testCreateTraining() {
            Trainee trainee = new Trainee();
            trainee.setId("t300");
            trainee.setFirstName("Greg");
            trainee.setLastName("Harris");
            trainee.setActive(true);
            trainee.setDateOfBirth(LocalDate.of(1994, 7, 2));
            trainee.setAddress("Addr");
            traineeService.createTrainee(trainee);

            Trainer trainer = new Trainer();
            trainer.setId("tr300");
            trainer.setFirstName("Hank");
            trainer.setLastName("Moore");
            trainer.setActive(true);
            trainer.setSpecialization("Cardio");

        trainerService.createTrainer(trainer);

        Training training = new Training("train300", trainee, trainer,
                "Evening Cardio", new TrainingType("Cardio"), LocalDate.now(), 60);

        Training saved = trainingService.createTraining(training);
        assertNotNull(saved);
        assertEquals("Evening Cardio", saved.getTrainingName());
    }
}