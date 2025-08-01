package com.gymapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    private String id;
    private Trainee trainee;           // Relacion con el trainee
    private Trainer trainer;           // Relacion con el trainer
    private String trainingName;
    private TrainingType trainingType; // Relacion con el TrainingType
    private LocalDate trainingDate;
    private int trainingDuration; // Duración en minutos
}
