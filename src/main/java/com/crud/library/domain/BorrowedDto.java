package com.crud.library.domain;

import java.time.LocalDate;

public class BorrowedDto {
    private int id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int copyId;
    private int userId;

    public BorrowedDto(int id, int copyId, int userId) {
        this.id = id;
        this.borrowDate = LocalDate.now();
        this.copyId = copyId;
        this.userId = userId;
    }

    public BorrowedDto() {
    }

    public BorrowedDto(int id, LocalDate borrowDate, LocalDate returnDate, int copyId, int userId) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.copyId = copyId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
