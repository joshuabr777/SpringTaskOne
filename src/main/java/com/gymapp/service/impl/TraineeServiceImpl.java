package com.gymapp.service.impl;

import com.gymapp.dao.TraineeDAO;
import com.gymapp.model.Trainee;
import com.gymapp.service.TraineeService;
import com.gymapp.util.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {

    private static final Logger logger = LoggerFactory.getLogger(TraineeServiceImpl.class);

    private final TraineeDAO traineeDAO;

    @Autowired
    public TraineeServiceImpl(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Override
    public Trainee createTrainee(Trainee trainee) {
        logger.info("Creating trainee: {}", trainee);
        if (trainee.getUsername() == null || trainee.getUsername().isEmpty()) {
            List<String> existingUsernames = traineeDAO.findAll().stream()
                    .map(Trainee::getUsername)
                    .toList();
            trainee.setUsername(Helpers.generateUsername(trainee.getFirstName(), trainee.getLastName(), existingUsernames));
            logger.debug("Generated username for trainee: {}", trainee.getUsername());
        }
        if (trainee.getPassword() == null || trainee.getPassword().isEmpty()) {
            trainee.setPassword(Helpers.generatePassword());
            logger.debug("Generated password for trainee");
        }
        traineeDAO.create(trainee);
        logger.info("Trainee created successfully: {}", trainee);
        return trainee;
    }

    @Override
    public Trainee updateTrainee(Trainee trainee) {
        logger.info("Updating trainee: {}", trainee);
        traineeDAO.update(trainee);
        logger.info("Trainee updated successfully: {}", trainee);
        return trainee;
    }

    @Override
    public void deleteTrainee(String id) {
        logger.info("Deleting trainee with id: {}", id);
        traineeDAO.delete(id);
        logger.info("Trainee deleted successfully with id: {}", id);
    }

    @Override
    public Trainee getTraineeById(String id) {
        logger.info("Fetching trainee by id: {}", id);
        return traineeDAO.findById(id);
    }

    @Override
    public List<Trainee> getAllTrainees() {
        logger.info("Fetching all trainees");
        return traineeDAO.findAll();
    }
}