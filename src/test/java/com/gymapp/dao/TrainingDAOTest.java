package com.gymapp.dao;

import com.gymapp.config.AppConfig;
import com.gymapp.model.*;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainingDAOTest {

    private static AnnotationConfigApplicationContext context;
    private static TrainingDAO trainingDAO;
    private static TraineeDAO traineeDAO;
    private static TrainerDAO trainerDAO;

    @BeforeAll
    static void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        trainingDAO = context.getBean(TrainingDAO.class);
        traineeDAO = context.getBean(TraineeDAO.class);
        trainerDAO = context.getBean(TrainerDAO.class);
    }

    @AfterAll
    static void cleanup() {
        context.close();
    }

    @Test
    @Order(1)
    void testFindAll() {
        List<Training> trainings = trainingDAO.findAll();
        assertFalse(trainings.isEmpty(), "Trainings list should not be empty");
    }

    @Test
    @Order(2)
    void testCreateAndFindById() {
        Trainee trainee = traineeDAO.findAll().get(0);
        Trainer trainer = trainerDAO.findAll().get(0);
        TrainingType type = new TrainingType("Strength");

        Training training = new Training("train100", trainee, trainer, "Test Training",
                type, LocalDate.now(), 90);
        trainingDAO.create(training);

        Training found = trainingDAO.findById("train100");
        assertNotNull(found);
        assertEquals("Test Training", found.getTrainingName());
    }
}