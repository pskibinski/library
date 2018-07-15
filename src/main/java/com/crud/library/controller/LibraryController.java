package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.facade.LibraryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Autowired
    private LibraryFacade libraryFacade;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        libraryFacade.addUser(userDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        return libraryFacade.findAllUsers();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam int userId) throws UserNotFoundException {
        libraryFacade.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        libraryFacade.addBook(bookDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return libraryFacade.findAllBooks();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCopy", consumes = APPLICATION_JSON_VALUE)
    public void addCopy(@RequestBody CopyOfTheBookDto copyOfTheBookDto, @RequestParam int bookId) throws BookNotFoundException {
        libraryFacade.addCopy(copyOfTheBookDto, bookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<CopyOfTheBookDto> getCopies() {
        return libraryFacade.findAllCopies();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableCopies")
    public List<CopyOfTheBookDto> getCopiesOfBook(@RequestParam int bookId) throws BookNotFoundException {
        return libraryFacade.findAllAvailableCopies(bookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "checkStatus")
    public boolean checkStatus(@RequestParam int copyId) throws CopyNotFoundException {
        return libraryFacade.checkCopyStatus(copyId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowedDto borrowedDto, @RequestParam int userId, @RequestParam int copyId) throws UserNotFoundException, BookNotFoundException {
        libraryFacade.borrowBook(borrowedDto, userId, copyId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBorrows")
    public List<BorrowedDto> getBorrows() {
        return libraryFacade.findAllBorrows();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public BorrowedDto returnBook(@RequestParam int borrowId) throws BorrowNotFoundException {
        return libraryFacade.returnBook(borrowId);
    }


}
