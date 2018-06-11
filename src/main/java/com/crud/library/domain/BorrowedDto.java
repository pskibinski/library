package com.crud.library.domain;

import java.time.LocalDate;

public class BorrowedDto {
    private int id;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowedDto(int id, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowedDto() {
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
}
