package com.crud.library.domain;

public class CopyOfTheBookDto {
    private int id;
    private int bookId;

    public CopyOfTheBookDto() {
    }

    public CopyOfTheBookDto(int id, int bookId) {
        this.id = id;
        this.bookId = bookId;
    }

    public CopyOfTheBookDto(int bookId) {
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
