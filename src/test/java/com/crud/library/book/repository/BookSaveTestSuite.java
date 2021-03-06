package com.crud.library.book.repository;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.mapper.CopyOfTheBookMapper;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowedRepository;
import com.crud.library.repository.CopyOfTheBookRepository;
import com.crud.library.repository.UserRepository;
import com.crud.library.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookSaveTestSuite {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CopyOfTheBookRepository copyOfTheBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BorrowedRepository borrowedRepository;

    @Autowired
    private DbService dbService;

    @Autowired
    private CopyOfTheBookMapper copyOfTheBookMapper;

    @Test
    public void testBookRepositorySave() throws BookNotFoundException {

        //Given
        User user1 = new User("John", "Smith");
        Book book1 = new Book("Jarosław Grzędowicz", "Pan Lodowego Ogrodu t.1", 2005);
        Book book2 = new Book("Jarosław Grzędowicz", "Pan Lodowego Ogrodu t.2", 2007);
        Book book3 = new Book("Jarosław Grzędowicz", "Pan Lodowego Ogrodu t.3", 2009);
        Book book4 = new Book("Jarosław Grzędowicz", "Pan Lodowego Ogrodu t.4", 2012);
        CopyOfTheBook copy1 = new CopyOfTheBook();
        CopyOfTheBook copy2 = new CopyOfTheBook();
        CopyOfTheBook copy3 = new CopyOfTheBook();
        CopyOfTheBook copy4 = new CopyOfTheBook();
        CopyOfTheBook copy5 = new CopyOfTheBook();
        CopyOfTheBook copy6 = new CopyOfTheBook();
        CopyOfTheBook copy7 = new CopyOfTheBook();
        CopyOfTheBook copy8 = new CopyOfTheBook();
        Borrowed borrowed1 = new Borrowed();
        Borrowed borrowed2 = new Borrowed();

        book1.getCopiesOfBook().add(copy1);
        book1.getCopiesOfBook().add(copy2);
        book1.getCopiesOfBook().add(copy3);
        book1.getCopiesOfBook().add(copy4);
        book2.getCopiesOfBook().add(copy5);
        book2.getCopiesOfBook().add(copy6);
        book3.getCopiesOfBook().add(copy7);
        book4.getCopiesOfBook().add(copy8);

        copy1.setBook(book1);
        copy2.setBook(book1);
        copy3.setBook(book1);
        copy4.setBook(book1);
        copy5.setBook(book2);
        copy6.setBook(book2);
        copy7.setBook(book3);
        copy8.setBook(book4);

        user1.getBorrowedBooks().add(borrowed1);
        user1.getBorrowedBooks().add(borrowed2);
        borrowed1.setUser(user1);
        borrowed2.setUser(user1);
        borrowed1.setCopyOfTheBook(copy1);
        borrowed2.setCopyOfTheBook(copy2);


        //When
        userRepository.save(user1);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        copyOfTheBookRepository.save(copy1);
        copyOfTheBookRepository.save(copy2);
        copyOfTheBookRepository.save(copy3);
        copyOfTheBookRepository.save(copy4);
        copyOfTheBookRepository.save(copy5);
        copyOfTheBookRepository.save(copy6);
        copyOfTheBookRepository.save(copy7);
        copyOfTheBookRepository.save(copy8);
        borrowedRepository.save(borrowed1);
        borrowedRepository.save(borrowed2);

        Book book = dbService.findBookById(book1.getId()).orElseThrow(BookNotFoundException::new);

        //Then
        System.out.println(book.getCopiesOfBook());
    }
}
