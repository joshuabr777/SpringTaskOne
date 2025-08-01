package com.gymapp.facade.impl;

import com.gymapp.facade.TraineeFacade;
import com.gymapp.model.Trainee;
import com.gymapp.service.TraineeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TraineeFacadeImpl implements TraineeFacade {

    private static final Logger logger = LoggerFactory.getLogger(TraineeFacadeImpl.class);
    private final TraineeService traineeService;

    @Autowired
    public TraineeFacadeImpl(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        logger.info("Creating trainee: {} {}", trainee.getFirstName(), trainee.getLastName());
        return traineeService.createTrainee(trainee);
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        logger.info("Updating trainee with ID: {}", trainee.getId());
        return traineeService.updateTrainee(trainee);
    }

    @Override
    public void deleteTrainee(String id) {
        logger.info("Deleting trainee with ID: {}", id);
        traineeService.deleteTrainee(id);
    }

    @Override
    public Trainee getTrainee(String id) {
        logger.info("Fetching trainee with ID: {}", id);
        return traineeService.getTraineeById(id);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        logger.info("Fetching all trainees");
        return traineeService.getAllTrainees();
    }
}