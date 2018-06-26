package com.crud.library.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate accCreated;
    private List<Integer> borrowId = new ArrayList<>();

    public UserDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto(int id, String firstName, String lastName, LocalDate accCreated, List<Integer> borrowId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accCreated = accCreated;
        this.borrowId = borrowId;
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

    public List<Integer> getBorrowId() {
        return borrowId;
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

    public void setBorrowId(List<Integer> borrowId) {
        this.borrowId = borrowId;
    }
}
