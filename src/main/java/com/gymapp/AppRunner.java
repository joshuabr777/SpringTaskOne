package com.gymapp;

import com.gymapp.config.AppConfig;
import com.gymapp.facade.TraineeFacade;
import com.gymapp.model.Trainee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class AppRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            TraineeFacade facade = context.getBean(TraineeFacade.class);

            Trainee trainee = new Trainee();
            trainee.setId("ec2test");
            trainee.setFirstName("EC2");
            trainee.setLastName("User");
            trainee.setActive(true);
            trainee.setDateOfBirth(LocalDate.of(2000, 1, 1));
            trainee.setAddress("Cloud Address");

            facade.createTrainee(trainee);
            logger.info("✅ EC2 deployment successful: Trainee created with username = {}", trainee.getUsername());
        }
    }
}