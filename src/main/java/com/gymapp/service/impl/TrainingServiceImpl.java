package com.gymapp.service.impl;

import com.gymapp.dao.TrainingDAO;
import com.gymapp.model.Training;
import com.gymapp.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingServiceImpl(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Override
    public Training createTraining(Training training) {
        logger.info("Creating training: {}", training);
        trainingDAO.create(training);
        logger.debug("Training created: {}", training);
        return training;
    }

    @Override
    public Training getTrainingById(String id) {
        logger.info("Fetching training by id: {}", id);
        Training training = trainingDAO.findById(id);
        logger.debug("Fetched training: {}", training);
        return training;
    }

    @Override
    public List<Training> getAllTrainings() {
        logger.info("Fetching all trainings");
        List<Training> trainings = trainingDAO.findAll();
        logger.debug("Fetched trainings: {}", trainings);
        return trainings;
    }
}