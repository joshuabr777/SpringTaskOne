package com.gymapp.facade;

import com.gymapp.config.AppConfig;
import com.gymapp.model.Trainee;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TraineeFacadeTest {

    private static AnnotationConfigApplicationContext context;
    private static TraineeFacade traineeFacade;

    @BeforeAll
    static void setup() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        traineeFacade = context.getBean(TraineeFacade.class);
    }

    @AfterAll
    static void teardown() {
        context.close();
    }

    @Test
    @Order(1)
    void testCreateGeneratesUsernameAndPassword() {
        Trainee trainee = new Trainee();
        trainee.setId("tf100");
        trainee.setFirstName("Alice");
        trainee.setLastName("Jones");
        trainee.setDateOfBirth(LocalDate.of(1990, 1, 1));
        trainee.setAddress("Some Address");

        Trainee saved = traineeFacade.createTrainee(trainee);

        assertNotNull(saved.getUsername(), "Username should be generated");
        assertEquals(10, saved.getPassword().length(), "Password should be 10 characters long");
    }

    @Test
    @Order(2)
    void testDuplicateUsernameAddsSuffix() {
        Trainee t1 = new Trainee();
        t1.setId("tf101");
        t1.setFirstName("Bob");
        t1.setLastName("King");
        t1.setDateOfBirth(LocalDate.of(1991, 2, 2));
        t1.setAddress("Addr");

        Trainee t2 = new Trainee();
        t2.setId("tf102");
        t2.setFirstName("Bob");
        t2.setLastName("King");
        t2.setDateOfBirth(LocalDate.of(1992, 3, 3));
        t2.setAddress("Addr");

        traineeFacade.createTrainee(t1);
        Trainee saved2 = traineeFacade.createTrainee(t2);

        assertNotEquals(t1.getUsername(), saved2.getUsername(),
                "Second trainee with same name should get a unique username");
    }

    @Test
    @Order(3)
    void testUpdateAndDelete() {
        Trainee trainee = new Trainee();
        trainee.setId("tf103");
        trainee.setFirstName("Charlie");
        trainee.setLastName("West");
        trainee.setDateOfBirth(LocalDate.of(1993, 4, 4));
        trainee.setAddress("Addr");
        traineeFacade.createTrainee(trainee);

        trainee.setAddress("Updated");
        traineeFacade.updateTrainee(trainee);
        assertEquals("Updated", traineeFacade.getTrainee("tf103").getAddress());

        traineeFacade.deleteTrainee("tf103");
        assertNull(traineeFacade.getTrainee("tf103"));
    }

    @Test
    @Order(4)
    void testGetAllTrainees() {
        List<Trainee> all = traineeFacade.getAllTrainees();
        assertFalse(all.isEmpty(), "Should return at least one trainee");
    }
}