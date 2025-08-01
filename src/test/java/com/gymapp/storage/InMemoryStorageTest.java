package com.gymapp.storage;

import com.gymapp.config.AppConfig;
import com.gymapp.model.Trainee;
import com.gymapp.model.Trainer;
import com.gymapp.model.Training;
import com.gymapp.model.TrainingType;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class InMemoryStorageTest {
    
    @Test
    void testDataLoading() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        InMemoryStorage storage = context.getBean(InMemoryStorage.class);

        // Validate trainees
        Map<String, Trainee> trainees = storage.getTrainees();
        assertFalse(trainees.isEmpty(), "Trainees should not be empty");

        // Validate trainers
        Map<String, Trainer> trainers = storage.getTrainers();
        assertFalse(trainers.isEmpty(), "Trainers should not be empty");

        // Validate training types
        Map<String, TrainingType> trainingTypes = storage.getTrainingTypes();
        assertFalse(trainingTypes.isEmpty(), "Training types should not be empty");

        // Validate trainings
        Map<String, Training> trainings = storage.getTrainings();
        assertFalse(trainings.isEmpty(), "Trainings should not be empty");

        context.close();
    }
    
}