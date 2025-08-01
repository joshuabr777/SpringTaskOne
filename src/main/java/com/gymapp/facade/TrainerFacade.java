package com.gymapp.facade;

import com.gymapp.model.Trainer;
import java.util.List;

public interface TrainerFacade {
    Trainer createTrainer(Trainer trainer);
    Trainer updateTrainer(Trainer trainer);
    Trainer getTrainer(String id);
    List<Trainer> getAllTrainers();
}