package com.gymapp.facade;

import com.gymapp.model.Trainee;
import java.util.List;

public interface TraineeFacade {
    Trainee createTrainee(Trainee trainee);
    Trainee updateTrainee(Trainee trainee);
    void deleteTrainee(String id);
    Trainee getTrainee(String id);
    List<Trainee> getAllTrainees();
}