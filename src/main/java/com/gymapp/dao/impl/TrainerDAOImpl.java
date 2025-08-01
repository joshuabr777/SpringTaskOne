package com.gymapp.dao.impl;

import com.gymapp.dao.TrainerDAO;
import com.gymapp.model.Trainer;
import com.gymapp.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainerDAOImpl implements TrainerDAO {

    private InMemoryStorage storage;

    @Autowired
    public void setStorage(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void create(Trainer trainer) {
        storage.getTrainers().put(trainer.getId(), trainer);
    }

    @Override
    public void update(Trainer trainer) {
        storage.getTrainers().put(trainer.getId(), trainer);
    }

    @Override
    public Trainer findById(String id) {
        return storage.getTrainers().get(id);
    }

    @Override
    public List<Trainer> findAll() {
        return new ArrayList<>(storage.getTrainers().values());
    }
}
