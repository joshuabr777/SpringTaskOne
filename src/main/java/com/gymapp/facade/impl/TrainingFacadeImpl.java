package com.gymapp.facade.impl;

import com.gymapp.facade.TrainingFacade;
import com.gymapp.model.Training;
import com.gymapp.service.TrainingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingFacadeImpl implements TrainingFacade {

    private static final Logger logger = LoggerFactory.getLogger(TrainingFacadeImpl.class);
    private final TrainingService trainingService;

    @Autowired
    public TrainingFacadeImpl(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Override
    public Training createTraining(Training training) {
        logger.info("Creating training: {}", training.getTrainingName());
        return trainingService.createTraining(training);
    }

    @Override
    public Training getTraining(String id) {
        logger.info("Fetching training with ID: {}", id);
        return trainingService.getTrainingById(id);
    }

    @Override
    public List<Training> getAllTrainings() {
        logger.info("Fetching all trainings");
        return trainingService.getAllTrainings();
    }
}