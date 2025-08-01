package com.gymapp.service;

import com.gymapp.model.Trainer;
import java.util.List;

public interface TrainerService {
    Trainer createTrainer(Trainer trainer);
    Trainer updateTrainer(Trainer trainer);
    Trainer getTrainerById(String id);
    List<Trainer> getAllTrainers();
}