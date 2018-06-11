package com.crud.library.domain;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.repository.BookRepository;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "COPY_OF_THE_BOOK")
public class CopyOfTheBook {

    private BookRepository bookRepository;

    private int id;
    private Book book;

    public CopyOfTheBook(int id, int bookid) throws BookNotFoundException {
        this.id = id;
        setBook(bookRepository.findById(bookid).orElseThrow(BookNotFoundException::new));

    }

    public CopyOfTheBook(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    public CopyOfTheBook() {
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
