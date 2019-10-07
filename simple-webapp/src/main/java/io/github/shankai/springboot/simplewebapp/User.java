package io.github.shankai.springboot.simplewebapp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private String id;
    private String name;
    private int age;
    
}