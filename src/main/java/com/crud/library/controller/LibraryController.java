package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.BorrowedMapper;
import com.crud.library.mapper.CopyOfTheBookMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        dbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbService.findBooks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCopy", consumes = APPLICATION_JSON_VALUE)
    public void addCopy(@RequestBody CopyOfTheBookDto copyOfTheBookDto) {
        dbService.saveCopy(copyOfTheBookMapper.mapToCopyOfTheBook(copyOfTheBookDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableCopies")
    public List<CopyOfTheBookDto> getCopiesOfBook(@RequestParam int id) {
        List<CopyOfTheBook> availableCopies = dbService.findAllCopiesByBookId(id)
                .stream()
                .filter(copy -> dbService.checkStatus(copy))
                .collect(Collectors.toList());
        return copyOfTheBookMapper.mapToCopyOfTheBookDtoList(availableCopies);
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowedDto borrowedDto) {
        dbService.saveBorrowed(borrowedMapper.mapToBorrowed(borrowedDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public BorrowedDto returnBook(@RequestBody BorrowedDto borrowedDto) {
        return borrowedMapper.mapToBorrowedDto(dbService.saveBorrowed(borrowedMapper.mapToBorrowed(borrowedDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "checkStatus")
    public boolean checkStatus(@RequestParam int id) throws BookNotFoundException {
        return dbService.checkStatus(dbService.findById(id).orElseThrow(BookNotFoundException::new));
    }
}
