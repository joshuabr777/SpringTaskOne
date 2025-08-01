package com.gymapp.service;

import com.gymapp.config.AppConfig;
import com.gymapp.model.Trainee;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TraineeServiceTest {

    private static AnnotationConfigApplicationContext context;
    private static TraineeService traineeService;

    @BeforeAll
    static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        traineeService = context.getBean(TraineeService.class);
    }

    @AfterAll
    static void teardown() {
        context.close();
    }

    @Test
    @Order(1)
    void testCreateGeneratesUsernameAndPassword() {
        Trainee trainee = new Trainee();
        trainee.setId("t200");
        trainee.setFirstName("Alice");
        trainee.setLastName("Walker");
        trainee.setDateOfBirth(LocalDate.of(1990, 5, 15));
        trainee.setAddress("123 Road");

        Trainee saved = traineeService.createTrainee(trainee);

        assertNotNull(saved.getUsername(), "Username should be generated");
        assertEquals(10, saved.getPassword().length(), "Password should be 10 characters");
    }

    @Test
    @Order(2)
    void testDuplicateUsernameGetsSerialSuffix() {
        Trainee t1 = new Trainee();
        t1.setId("t201");
        t1.setFirstName("Bob");
        t1.setLastName("Smith");
        t1.setActive(true);
        t1.setDateOfBirth(LocalDate.of(1991, 3, 10));
        t1.setAddress("Addr");

        Trainee t2 = new Trainee();
        t2.setId("t202");
        t2.setFirstName("Bob");
        t2.setLastName("Smith");
        t2.setActive(true);
        t2.setDateOfBirth(LocalDate.of(1992, 4, 12));
        t2.setAddress("Addr");

        traineeService.createTrainee(t1);
        Trainee saved2 = traineeService.createTrainee(t2);

        List<Trainee> all = traineeService.getAllTrainees();
        assertTrue(all.stream().anyMatch(t -> t.getUsername().startsWith("bob.smith")),
                "Username should start with bob.smith");
        assertNotEquals(t1.getUsername(), saved2.getUsername(),
                "Second trainee should get a unique username with serial suffix");
    }

    @Test
    @Order(3)
    void testUpdateAndDelete() {
        Trainee trainee = new Trainee();
        trainee.setId("t203");
        trainee.setFirstName("Charlie");
        trainee.setLastName("Brown");
        trainee.setActive(true);
        trainee.setDateOfBirth(LocalDate.of(1985, 6, 1));
        trainee.setAddress("Addr");

        traineeService.createTrainee(trainee);

        trainee.setAddress("Updated Address");
        traineeService.updateTrainee(trainee);

        Trainee updated = traineeService.getTraineeById("t203");
        assertEquals("Updated Address", updated.getAddress());

        traineeService.deleteTrainee("t203");
        assertNull(traineeService.getTraineeById("t203"));
    }
}