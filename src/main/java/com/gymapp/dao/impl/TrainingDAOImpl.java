package com.gymapp.dao.impl;

import com.gymapp.dao.TrainingDAO;
import com.gymapp.model.Training;
import com.gymapp.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainingDAOImpl implements TrainingDAO {

    private InMemoryStorage storage;

    @Autowired
    public void setStorage(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void create(Training training) {
        storage.getTrainings().put(training.getId(), training);
    }

    @Override
    public Training findById(String id) {
        return storage.getTrainings().get(id);
    }

    @Override
    public List<Training> findAll() {
        return new ArrayList<>(storage.getTrainings().values());
    }
}
