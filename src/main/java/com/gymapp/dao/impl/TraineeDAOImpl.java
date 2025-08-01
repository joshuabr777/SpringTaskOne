package com.gymapp.dao.impl;

import com.gymapp.dao.TraineeDAO;
import com.gymapp.model.Trainee;
import com.gymapp.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TraineeDAOImpl implements TraineeDAO {

    private InMemoryStorage storage;

    @Autowired
    public void setStorage(InMemoryStorage storage) {
        this.storage = storage;
    }

    @Override
    public void create(Trainee trainee) {
        storage.getTrainees().put(trainee.getId(), trainee);
    }

    @Override
    public void update(Trainee trainee) {
        storage.getTrainees().put(trainee.getId(), trainee);
    }

    @Override
    public void delete(String id) {
        storage.getTrainees().remove(id);
    }

    @Override
    public Trainee findById(String id) {
        return storage.getTrainees().get(id);
    }

    @Override
    public List<Trainee> findAll() {
        return new ArrayList<>(storage.getTrainees().values());
    }
}