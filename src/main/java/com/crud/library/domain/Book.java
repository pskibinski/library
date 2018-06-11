package com.crud.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Table(name="BOOKS")
@Entity
public class Book {
    private int id;
    private String author;
    private String title;
    private int year;
    private List<CopyOfTheBook> copiesOfBook = new ArrayList<>();

    public Book(int id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public Book(String author, String title, int year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public Book() {
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique =  true)
    public int getId() {
        return id;
    }

    @Column(name="author")
    public String getAuthor() {
        return author;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    @Column(name="year")
    public int getYear() {
        return year;
    }

    @OneToMany(targetEntity = CopyOfTheBook.class, mappedBy = "book", fetch = FetchType.LAZY)
    public List<CopyOfTheBook> getCopysOfBook() {
        return copiesOfBook;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setYear(int year) {
        this.year = year;
    }

    public void setCopysOfBook(List<CopyOfTheBook> copysOfBook) {
        this.copiesOfBook = copysOfBook;
    }
}
