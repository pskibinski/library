package com.crud.library.domain;

import java.util.List;

public class BookDto {
    private int id;
    private String author;
    private String title;
    private int year;
    private List<CopyOfTheBook> copiesList;

    public BookDto(int id, String author, String title, int year, List<CopyOfTheBook> copiesList) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.copiesList = copiesList;
    }

    public BookDto() {
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<CopyOfTheBook> getCopiesList() {
        return copiesList;
    }
}
