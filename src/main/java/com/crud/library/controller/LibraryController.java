package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.BorrowedMapper;
import com.crud.library.mapper.CopyOfTheBookMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

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

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        dbService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(dbService.findUsers());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        ofNullable(bookDto.getCopyId()).orElse(new ArrayList<>());
        dbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbService.findBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCopy", consumes = APPLICATION_JSON_VALUE)
    public void addCopy(@RequestBody CopyOfTheBookDto copyOfTheBookDto, @RequestParam int id) throws BookNotFoundException {
        Book book = dbService.findBookById(id).orElseThrow(BookNotFoundException::new);
        copyOfTheBookDto.setBookId(id);
        CopyOfTheBook copy = copyOfTheBookMapper.mapToCopyOfTheBook(copyOfTheBookDto);
        book.getCopiesOfBook().add(copy);
        copy.setBook(book);
        dbService.saveBook(book);
        dbService.saveCopy(copy);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<CopyOfTheBookDto> getCopies() {
        return copyOfTheBookMapper.mapToCopyOfTheBookDtoList(dbService.findAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableCopies")
    public List<CopyOfTheBookDto> getCopiesOfBook(@RequestParam int id) throws BookNotFoundException {
        List<CopyOfTheBook> availableCopies = dbService.findAllAvailableCopiesByBookId(id);
        return copyOfTheBookMapper.mapToCopyOfTheBookDtoList(availableCopies);
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowedDto borrowedDto, @RequestParam int userId, @RequestParam int copyId) throws UserNotFoundException, BookNotFoundException {
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

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public BorrowedDto returnBook(@RequestParam int borrowId) throws BookNotFoundException {
        Borrowed borrowed = dbService.findBorrowById(borrowId).orElseThrow(BookNotFoundException::new);
        borrowed.setReturnDate(LocalDate.now());
        dbService.saveBorrowed(borrowed);
        return borrowedMapper.mapToBorrowedDto(borrowed);
    }

    @RequestMapping(method = RequestMethod.GET, value = "checkStatus")
    public boolean checkStatus(@RequestParam int id) throws BookNotFoundException {
        return dbService.checkStatus(dbService.findById(id).orElseThrow(BookNotFoundException::new));
    }
}
