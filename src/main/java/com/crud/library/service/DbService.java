package com.crud.library.service;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.controller.UserNotFoundException;
import com.crud.library.domain.Book;
import com.crud.library.domain.Borrowed;
import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.User;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowedRepository;
import com.crud.library.repository.CopyOfTheBookRepository;
import com.crud.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CopyOfTheBookRepository copyOfTheBookRepository;

    @Autowired
    private BorrowedRepository borrowedRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int userId) throws UserNotFoundException {
        userRepository.delete(userRepository.findUserById(userId).orElseThrow(UserNotFoundException::new));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public CopyOfTheBook saveCopy(CopyOfTheBook copyOfTheBook) {
        return copyOfTheBookRepository.save(copyOfTheBook);
    }

    public List<CopyOfTheBook> findAllCopies() {
        return copyOfTheBookRepository.findAll();
    }

    public List<CopyOfTheBook> findAllCopiesByBookId(int id) {
        return copyOfTheBookRepository.findAllByBook_Id(id);
    }

    public Optional<Borrowed> findBorrowById(int id) {
        return borrowedRepository.findById(id);
    }

    public List<CopyOfTheBook> findAllAvailableCopiesByBookId(int bookId) throws BookNotFoundException {
        return findBookById(bookId).orElseThrow(BookNotFoundException::new).getCopiesOfBook().stream()
                .filter(c -> checkStatus(c))
                .collect(Collectors.toList());
    }

    public Optional<CopyOfTheBook> findById(int id) {
        return copyOfTheBookRepository.findById(id);
    }

    public Optional<Book> findBookById(int id) {
        return bookRepository.findById(id);
    }

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public boolean checkStatus(CopyOfTheBook copyOfTheBook) {
        return borrowedRepository.findByCopyOfTheBookAndReturnDate(copyOfTheBook, null).size() == 0;
    }

    public Borrowed saveBorrowed(Borrowed borrowed) {
        return borrowedRepository.save(borrowed);
    }

    public List<Borrowed> findBorrowed() {
        return borrowedRepository.findAll();
    }

    public Optional<User> findUser(int id) {
        return userRepository.findUserById(id);
    }

}
