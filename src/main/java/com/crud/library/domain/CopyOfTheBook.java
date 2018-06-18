package com.crud.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "COPY_OF_THE_BOOK")
public class CopyOfTheBook {

    private int id;
    private Book book;

    public CopyOfTheBook() {
    }

    public CopyOfTheBook(int id) {
        this.id = id;
    }

    public CopyOfTheBook(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique =  true)
    public int getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    private void setId(int id) {
        this.id = id;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
