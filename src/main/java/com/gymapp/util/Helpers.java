package com.gymapp.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Helpers {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;
    private static final SecureRandom random = new SecureRandom();
    private static final Logger logger = Logger.getLogger(Helpers.class.getName());

    private Helpers() {
        // Private constructor to prevent instantiation
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static String generatePassword() {
        String password = generateRandomPassword();
        logger.log(Level.INFO, "Password generated: [HIDDEN]");
        return password;
    }

    private static String generateRandomPassword() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String generateUsername(String firstName, String lastName, List<String> existingUsernames) {
        String base = firstName.toLowerCase() + "." + lastName.toLowerCase();
        String username = base;
        int counter = 1;
        while (true) {
            final String currentUsername = username;
            boolean exists = existingUsernames.stream()
                    .anyMatch(currentUsername::equalsIgnoreCase);
            if (!exists) {
                break;
            }
            username = base + counter++;
        }
        logger.log(Level.INFO, "Username generated for user: {0}.***", firstName.toLowerCase());
        return username;
    }
}