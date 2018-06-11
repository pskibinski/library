package com.crud.library.domain;

public class CopyOfTheBookDto {
    private int id;
    private Book book;

    public CopyOfTheBookDto(int id, Book book) {
        this.id = id;
        this.book = book;
    }

    public CopyOfTheBookDto() {
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }
}
