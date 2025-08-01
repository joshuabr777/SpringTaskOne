package com.gymapp.dao;

import com.gymapp.model.Training;
import java.util.List;

public interface TrainingDAO {
    void create(Training training);
    Training findById(String id);
    List<Training> findAll();
}
