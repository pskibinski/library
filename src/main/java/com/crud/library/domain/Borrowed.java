package com.crud.library.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "BORROWED_BOOKS")
public class Borrowed {

    private int id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private User user;
    private CopyOfTheBook copyOfTheBook;

    public Borrowed(int id, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Borrowed(int id, LocalDate borrowDate, LocalDate returnDate, User user, CopyOfTheBook copyOfTheBook) {
        this.id = id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.user = user;
        this.copyOfTheBook = copyOfTheBook;
    }

    public Borrowed() {
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique =  true)
    public int getId() {
        return id;
    }

    @Column(name = "borrow_date")
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    @Column(name = "return_date")
    public LocalDate getReturnDate() {
        return returnDate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copy_id")
    public CopyOfTheBook getCopyOfTheBook() {
        return copyOfTheBook;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCopyOfTheBook(CopyOfTheBook copyOfTheBook) {
        this.copyOfTheBook = copyOfTheBook;
    }
}
