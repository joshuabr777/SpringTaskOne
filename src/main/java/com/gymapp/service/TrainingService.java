package com.gymapp.service;

import com.gymapp.model.Training;
import java.util.List;

public interface TrainingService {
    Training createTraining(Training training);
    Training getTrainingById(String id);
    List<Training> getAllTrainings();
}