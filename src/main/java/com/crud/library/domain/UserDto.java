package com.crud.library.domain;

import java.time.LocalDate;

public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate accCreated;

    public UserDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accCreated = LocalDate.now();
    }

    public UserDto(int id, String firstName, String lastName, LocalDate accCreated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accCreated = accCreated;
    }

    public UserDto() {
        this.accCreated = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getAccCreated() {
        return accCreated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccCreated(LocalDate accCreated) {
        this.accCreated = accCreated;
    }
}
