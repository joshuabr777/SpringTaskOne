package com.gymapp.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gymapp.model.*;
import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class InMemoryStorage {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryStorage.class);
    
    private final Map<String, Trainee> trainees = new HashMap<>();
    private final Map<String, Trainer> trainers = new HashMap<>();
    private final Map<String, Training> trainings = new HashMap<>();
    private final Map<String, TrainingType> trainingTypes = new HashMap<>();

    @Value("${data.trainees.path}")
    private String traineesPath;

    @Value("${data.trainers.path}")
    private String trainersPath;

    @Value("${data.trainings.path}")
    private String trainingsPath;

    @Value("${data.trainingtypes.path}")
    private String trainingTypesPath;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void loadData() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        loadEntities(traineesPath, new TypeReference<List<Trainee>>() {}, trainees);
        loadEntities(trainersPath, new TypeReference<List<Trainer>>() {}, trainers);
        loadEntities(trainingTypesPath, new TypeReference<List<TrainingType>>() {}, trainingTypes);
        loadEntities(trainingsPath, new TypeReference<List<Training>>() {}, trainings);
    }

    /**
     * Loads a list of entities from a JSON file located at the specified path and populates the provided map with these entities,
     * using their IDs as keys. The method uses Jackson's {ObjectMapper} to deserialize the JSON content into a list of entities.
     * Each entity is expected to have a public {getId()} method returning a {String} identifier.
     */
    private <T> void loadEntities(String path, TypeReference<List<T>> type, Map<String, T> map) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is != null) {
                List<T> list = objectMapper.findAndRegisterModules().readValue(is, type);
                for (T entity : list) {
                    String key;
                    if (entity instanceof TrainingType) {
                        key = ((TrainingType) entity).getName();
                    } else {
                        key = ((String) entity.getClass().getMethod("getId").invoke(entity));
                    }
                    map.put(key, entity);
                }
                logger.info("Loaded {} entities from {}", list.size(), path);
            } else {
                logger.error("File not found: {}", path);
            }
        } catch (Exception e) {
            logger.error("Error loading data from {}: {}", path, e.getMessage());
        }
    }
}