package com.gymapp.dao;

import com.gymapp.model.Trainer;
import java.util.List;

public interface TrainerDAO {
    void create(Trainer trainer);
    void update(Trainer trainer);
    Trainer findById(String id);
    List<Trainer> findAll();
}
