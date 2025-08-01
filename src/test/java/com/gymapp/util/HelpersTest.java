package com.gymapp.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HelpersTest {

    @Test
    void testGeneratePasswordLength() {
        String pwd = Helpers.generatePassword();
        assertNotNull(pwd, "Password should not be null");
        assertEquals(10, pwd.length(), "Password should be exactly 10 characters");
    }

    @Test
    void testGeneratePasswordRandomness() {
        String pwd1 = Helpers.generatePassword();
        String pwd2 = Helpers.generatePassword();
        // Extremely low chance of collision, good enough for a unit test
        assertNotEquals(pwd1, pwd2, "Passwords should be different for each generation");
    }

    @Test
    void testGenerateUsernameBasic() {
        List<String> existing = Arrays.asList("john.doe");
        String username = Helpers.generateUsername("John", "Doe", existing);

        assertTrue(username.equalsIgnoreCase("john.doe1"),
                "Should append '1' because john.doe already exists");
    }

    @Test
    void testGenerateUsernameNoConflict() {
        List<String> existing = Arrays.asList("alice.smith");
        String username = Helpers.generateUsername("Bob", "Smith", existing);

        assertEquals("bob.smith", username, "Should generate base username without suffix when no conflict");
    }

    @Test
    void testGenerateUsernameMultipleConflicts() {
        List<String> existing = Arrays.asList("bob.smith", "bob.smith1", "bob.smith2");
        String username = Helpers.generateUsername("Bob", "Smith", existing);

        assertEquals("bob.smith3", username, "Should increment suffix to avoid duplicates");
    }
}