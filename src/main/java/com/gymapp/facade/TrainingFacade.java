package com.gymapp.facade;

import com.gymapp.model.Training;
import java.util.List;

public interface TrainingFacade {
    Training createTraining(Training training);
    Training getTraining(String id);
    List<Training> getAllTrainings();
}