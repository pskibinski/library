package com.crud.library.domain;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
    private int id;
    private String author;
    private String title;
    private int year;
    private List<Integer> copyId = new ArrayList<>();

    public BookDto(int id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public BookDto(String author, String title, int year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public BookDto() {
    }

    public BookDto(int id, String author, String title, int year, List<Integer> copyId) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
        this.copyId = copyId;
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

    public List<Integer> getCopyId() {
        return copyId;
    }
}
