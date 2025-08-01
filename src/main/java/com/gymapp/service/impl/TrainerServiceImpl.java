package com.gymapp.service.impl;

import com.gymapp.dao.TrainerDAO;
import com.gymapp.model.Trainer;
import com.gymapp.service.TrainerService;
import com.gymapp.util.Helpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

    private static final Logger logger = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private final TrainerDAO trainerDAO;

    @Autowired
    public TrainerServiceImpl(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        logger.info("Creating trainer: {}", trainer);
        if (trainer.getUsername() == null || trainer.getUsername().isEmpty()) {
            List<String> existingUsernames = trainerDAO.findAll().stream()
                    .map(Trainer::getUsername)
                    .toList();
            trainer.setUsername(Helpers.generateUsername(trainer.getFirstName(), trainer.getLastName(), existingUsernames));
            logger.debug("Generated username for trainer: {}", trainer.getUsername());
        }
        if (trainer.getPassword() == null || trainer.getPassword().isEmpty()) {
            trainer.setPassword(Helpers.generatePassword());
            logger.debug("Generated password for trainer");
        }
        trainerDAO.create(trainer);
        logger.info("Trainer created successfully: {}", trainer);
        return trainer;
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        logger.info("Updating trainer: {}", trainer);
        trainerDAO.update(trainer);
        logger.info("Trainer updated successfully: {}", trainer);
        return trainer;
    }

    @Override
    public Trainer getTrainerById(String id) {
        logger.info("Fetching trainer by id: {}", id);
        Trainer trainer = trainerDAO.findById(id);
        logger.debug("Fetched trainer: {}", trainer);
        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        logger.info("Fetching all trainers");
        List<Trainer> trainers = trainerDAO.findAll();
        logger.debug("Fetched trainers: {}", trainers);
        return trainers;
    }
}