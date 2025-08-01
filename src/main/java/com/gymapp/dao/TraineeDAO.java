package com.gymapp.dao;

import com.gymapp.model.Trainee;
import java.util.List;

public interface TraineeDAO {
    void create(Trainee trainee);
    void update(Trainee trainee);
    void delete(String id);
    Trainee findById(String id);
    List<Trainee> findAll();
}