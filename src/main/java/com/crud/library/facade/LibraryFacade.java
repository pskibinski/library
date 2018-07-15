package com.crud.library.facade;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.controller.BorrowNotFoundException;
import com.crud.library.controller.CopyNotFoundException;
import com.crud.library.controller.UserNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.BorrowedMapper;
import com.crud.library.mapper.CopyOfTheBookMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class LibraryFacade {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BorrowedMapper borrowedMapper;

    @Autowired
    private CopyOfTheBookMapper copyOfTheBookMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DbService dbService;

    public void addUser(UserDto userDto) {
        dbService.saveUser(userMapper.mapToUser(userDto));
    }

    public List<UserDto> findAllUsers() {
        return userMapper.mapToUserDtoList(dbService.findUsers());
    }

    public void deleteUser(int userId) throws UserNotFoundException {
        dbService.deleteUser(userId);
    }

    public void addBook(BookDto bookDto) {
        ofNullable(bookDto.getCopyId()).orElse(new ArrayList<>());
        dbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    public List<BookDto> findAllBooks() {
        return bookMapper.mapToBookDtoList(dbService.findBooks());
    }

    public void addCopy(CopyOfTheBookDto copyOfTheBookDto, int bookId) throws BookNotFoundException {
        Book book = dbService.findBookById(bookId).orElseThrow(BookNotFoundException::new);
        copyOfTheBookDto.setBookId(bookId);
        CopyOfTheBook copy = copyOfTheBookMapper.mapToCopyOfTheBook(copyOfTheBookDto);
        book.getCopiesOfBook().add(copy);
        copy.setBook(book);
        dbService.saveBook(book);
        dbService.saveCopy(copy);
    }

    public List<CopyOfTheBookDto> findAllCopies() {
        return copyOfTheBookMapper.mapToCopyOfTheBookDtoList(dbService.findAllCopies());
    }

    public List<CopyOfTheBookDto> findAllAvailableCopies(int bookId) throws BookNotFoundException {
        List<CopyOfTheBook> availableCopies = dbService.findAllAvailableCopiesByBookId(bookId);
        return copyOfTheBookMapper.mapToCopyOfTheBookDtoList(availableCopies);
    }

    public boolean checkCopyStatus(int copyId) throws CopyNotFoundException {
        return dbService.checkStatus(dbService.findById(copyId).orElseThrow(CopyNotFoundException::new));
    }

    public void borrowBook(BorrowedDto borrowedDto, int userId, int copyId) throws UserNotFoundException, BookNotFoundException {
        CopyOfTheBook copy = dbService.findById(copyId).orElseThrow(BookNotFoundException::new);
        User user = dbService.findUser(userId).orElseThrow(UserNotFoundException::new);
        borrowedDto.setBorrowDate(LocalDate.now());
        borrowedDto.setUserId(userId);
        Borrowed borrowed = borrowedMapper.mapToBorrowed(borrowedDto);
        user.getBorrowedBooks().add(borrowed);
        borrowed.setUser(user);
        borrowed.setCopyOfTheBook(copy);
        borrowedDto.setCopyId(copyId);
        borrowedDto.setUserId(userId);
        dbService.saveBorrowed(borrowed);
        dbService.saveUser(user);
    }

    public List<BorrowedDto> findAllBorrows() {
        return borrowedMapper.mapToBorrowedDtoList(dbService.findBorrowed());
    }

    public BorrowedDto returnBook(int borrowId) throws BorrowNotFoundException {
        Borrowed borrowed = dbService.findBorrowById(borrowId).orElseThrow(BorrowNotFoundException::new);
        borrowed.setReturnDate(LocalDate.now());
        dbService.saveBorrowed(borrowed);
        BorrowedDto borrowedDto = borrowedMapper.mapToBorrowedDto(borrowed);
        borrowedDto.setReturnDate(LocalDate.now());
        return borrowedDto;
    }
}
