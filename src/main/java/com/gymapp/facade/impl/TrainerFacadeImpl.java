package com.gymapp.facade.impl;

import com.gymapp.facade.TrainerFacade;
import com.gymapp.model.Trainer;
import com.gymapp.service.TrainerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainerFacadeImpl implements TrainerFacade {

    private static final Logger logger = LoggerFactory.getLogger(TrainerFacadeImpl.class);
    private final TrainerService trainerService;

    @Autowired
    public TrainerFacadeImpl(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        logger.info("Creating trainer: {} {}", trainer.getFirstName(), trainer.getLastName());
        return trainerService.createTrainer(trainer);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        logger.info("Updating trainer with ID: {}", trainer.getId());
        return trainerService.updateTrainer(trainer);
    }

    @Override
    public Trainer getTrainer(String id) {
        logger.info("Fetching trainer with ID: {}", id);
        return trainerService.getTrainerById(id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        logger.info("Fetching all trainers");
        return trainerService.getAllTrainers();
    }
}