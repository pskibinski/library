package com.crud.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "USERS")
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate accCreated;
    private List<Borrowed> borrowedBooks = new ArrayList<>();

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accCreated = LocalDate.now();
    }

    public User(int id, String firstName, String lastName, LocalDate accCreated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accCreated = accCreated;
    }

    public User() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique =  true)
    public int getId() {
        return id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @NotNull
    @Column(name = "created")
    public LocalDate getAccCreated() {
        return accCreated;
    }

    @OneToMany(targetEntity = Borrowed.class, mappedBy = "user", fetch = FetchType.LAZY)
    public List<Borrowed> getBorrowedBooks() {
        return borrowedBooks;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setAccCreated(LocalDate accCreated) {
        this.accCreated = accCreated;
    }

    public void setBorrowedBooks(List<Borrowed> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
