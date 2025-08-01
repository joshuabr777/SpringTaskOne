package com.gymapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
}
