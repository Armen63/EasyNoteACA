package com.example.easynoteaca.pojo;

import java.io.Serializable;

public class User implements Serializable {
    static final long serialVersionUID = -1;

    public enum Gender{
        MALE,
        FEMALE
    }

    private long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
    private int age;


    public User(String username, String email, String password, String firstName, String lastName, Gender gender, int age) {
        this.id = System.currentTimeMillis();
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

}
