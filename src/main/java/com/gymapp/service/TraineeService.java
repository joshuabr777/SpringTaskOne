package com.gymapp.service;

import com.gymapp.model.Trainee;
import java.util.List;

public interface TraineeService {
    Trainee createTrainee(Trainee trainee);
    Trainee updateTrainee(Trainee trainee);
    void deleteTrainee(String id);
    Trainee getTraineeById(String id);
    List<Trainee> getAllTrainees();
}