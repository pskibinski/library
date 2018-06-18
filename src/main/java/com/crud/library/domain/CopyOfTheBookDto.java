package com.crud.library.domain;

public class CopyOfTheBookDto {
    private int id;
    private Book book;

    public CopyOfTheBookDto() {
    }

    public CopyOfTheBookDto(int id) {
        this.id = id;
    }

    public CopyOfTheBookDto(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    public CopyOfTheBookDto(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
