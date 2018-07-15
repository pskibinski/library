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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LibraryFacadeTestSuite {

    @InjectMocks
    private LibraryFacade libraryFacade;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BorrowedMapper borrowedMapper;

    @Mock
    private CopyOfTheBookMapper copyOfTheBookMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private DbService dbService;

    @Test
    public void testAddUser() {
        //Given
        User user = new User(1, "John", "Smith", LocalDate.now());
        UserDto userDto = new UserDto(1, "John", "Smith", LocalDate.now(), new ArrayList<>());

        when(dbService.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUser(userDto)).thenReturn(user);

        //When
        libraryFacade.addUser(userDto);

        //Then
        verify(dbService, times(1)).saveUser(any(User.class));
        verify(userMapper, times(1)).mapToUser(any(UserDto.class));
    }

    @Test
    public void testFindAllUsers() {
        //Given
        User user = new User(1, "John", "Smith", LocalDate.now());
        UserDto userDto = new UserDto(1, "John", "Smith", LocalDate.now());
        List<User> users = new ArrayList<>();
        List<UserDto> userDtos = new ArrayList<>();
        users.add(user);
        userDtos.add(userDto);

        when(dbService.findUsers()).thenReturn(users);
        when(userMapper.mapToUserDtoList(users)).thenReturn(userDtos);

        //When
        List<UserDto> findedUsers = libraryFacade.findAllUsers();

        //Then
        assertEquals(1, findedUsers.size());
        assertEquals(1, findedUsers.get(0).getId());
        assertEquals(0, findedUsers.get(0).getBorrowId().size());
    }

    @Test
    public void testDeleteUser() throws UserNotFoundException {
        //Given
        User user = new User(1, "John", "Smith", LocalDate.now());

        doNothing().when(dbService).deleteUser(user.getId());

        //When
        libraryFacade.deleteUser(1);

        //Then
        verify(dbService, times(1)).deleteUser(1);
    }

    @Test
    public void testAddBook() {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        BookDto bookDto = new BookDto(1, "Frank Herbert", "Dune", 1965);

        when(dbService.saveBook(book)).thenReturn(book);
        when(bookMapper.mapToBook(bookDto)).thenReturn(book);

        //When
        libraryFacade.addBook(bookDto);

        //Then
        verify(dbService, times(1)).saveBook(any(Book.class));
        verify(bookMapper, times(1)).mapToBook(any(BookDto.class));
    }

    @Test
    public void testFindAllBooks() {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        BookDto bookDto = new BookDto(1, "Frank Herbert", "Dune", 1965);
        List<Book> books = new ArrayList<>();
        List<BookDto> bookDtos = new ArrayList<>();
        books.add(book);
        bookDtos.add(bookDto);

        when(dbService.findBooks()).thenReturn(books);
        when(bookMapper.mapToBookDtoList(books)).thenReturn(bookDtos);

        //When
        List<BookDto> findedBooks = libraryFacade.findAllBooks();

        //Then
        assertEquals(1, findedBooks.size());
        assertEquals("Dune", findedBooks.get(0).getTitle());
    }

    @Test
    public void testAddCopy() throws BookNotFoundException {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy = new CopyOfTheBook(1, book);
        CopyOfTheBookDto copyDto = new CopyOfTheBookDto(1, 1);
        book.getCopiesOfBook().add(copy);

        when(dbService.findBookById(1)).thenReturn(Optional.ofNullable(book));
        when(dbService.saveCopy(copy)).thenReturn(copy);
        when(copyOfTheBookMapper.mapToCopyOfTheBook(copyDto)).thenReturn(copy);

        //When
        libraryFacade.addCopy(copyDto, 1);

        //Then
        verify(dbService, times(1)).saveCopy(copy);
        verify(copyOfTheBookMapper, times(1)).mapToCopyOfTheBook(copyDto);
        verify(dbService, times(1)).findBookById(1);
    }

    @Test
    public void testFindAllCopies() {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy = new CopyOfTheBook(1, book);
        CopyOfTheBookDto copyDto = new CopyOfTheBookDto(1, 1);
        List<CopyOfTheBook> copies = new ArrayList<>();
        List<CopyOfTheBookDto> copiesDto = new ArrayList<>();
        copies.add(copy);
        copiesDto.add(copyDto);

        when(dbService.findAllCopies()).thenReturn(copies);
        when(copyOfTheBookMapper.mapToCopyOfTheBookDtoList(copies)).thenReturn(copiesDto);

        //When
        List<CopyOfTheBookDto> findedCopies = libraryFacade.findAllCopies();

        //Then
        assertEquals(1, findedCopies.size());
        assertEquals(1, findedCopies.get(0).getBookId());
    }

    @Test
    public void testFindAllAvailableCopies() throws BookNotFoundException {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy1 = new CopyOfTheBook(1, book);
        CopyOfTheBook copy2 = new CopyOfTheBook(2, book);
        CopyOfTheBookDto copyDto1 = new CopyOfTheBookDto(1, 1);
        CopyOfTheBookDto copyDto2 = new CopyOfTheBookDto(2, 1);
        List<CopyOfTheBook> copies = new ArrayList<>();
        List<CopyOfTheBookDto> copiesDto = new ArrayList<>();
        copies.add(copy1);
        copies.add(copy2);
        copiesDto.add(copyDto1);
        copiesDto.add(copyDto2);

        when(dbService.findAllAvailableCopiesByBookId(1)).thenReturn(copies);
        when(copyOfTheBookMapper.mapToCopyOfTheBookDtoList(copies)).thenReturn(copiesDto);

        //When
        List<CopyOfTheBookDto> findedCopies = libraryFacade.findAllAvailableCopies(1);

        //Then
        assertEquals(2, findedCopies.size());
        assertEquals(1, findedCopies.get(1).getBookId());
        assertEquals(2, findedCopies.get(1).getId());
    }

    @Test
    public void testCheckStatus() throws CopyNotFoundException {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy = new CopyOfTheBook(1, book);

        when(dbService.findById(copy.getId())).thenReturn(Optional.ofNullable(copy));
        when(dbService.checkStatus(copy)).thenReturn(true);

        //When
        Boolean isBorrow = libraryFacade.checkCopyStatus(copy.getId());

        //Then
        assertTrue(isBorrow);
    }

    @Test
    public void testBorrowBook() throws UserNotFoundException, BookNotFoundException{
        //Given
        Borrowed borrowed = new Borrowed(1, LocalDate.now(), null);
        BorrowedDto borrowedDto = new BorrowedDto(1, LocalDate.now(), null);
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy = new CopyOfTheBook(1, book);
        User user = new User(10, "John", "Smith", LocalDate.now());

        when(dbService.findById(1)).thenReturn(Optional.ofNullable(copy));
        when(dbService.saveBorrowed(borrowed)).thenReturn(borrowed);
        when(dbService.saveUser(user)).thenReturn(user);
        when(borrowedMapper.mapToBorrowed(borrowedDto)).thenReturn(borrowed);
        when(dbService.findUser(10)).thenReturn(Optional.ofNullable(user));


        //When
        libraryFacade.borrowBook(borrowedDto, 10, 1);

        //Then
        verify(dbService, times(1)).saveBorrowed(borrowed);
        verify(borrowedMapper, times(1)).mapToBorrowed(borrowedDto);
        verify(dbService, times(1)).findById(1);
        verify(dbService, times(1)).saveUser(user);
        verify(dbService, times(1)).findUser(10);
    }

    @Test
    public void testFindAllBorrows() {
        //Given
        Borrowed borrowed1 = new Borrowed(1, LocalDate.now(), null);
        Borrowed borrowed2 = new Borrowed(2, LocalDate.now().minusDays(5), LocalDate.now());
        BorrowedDto borrowedDto1 = new BorrowedDto(1, LocalDate.now(), null);
        BorrowedDto borrowedDto2 = new BorrowedDto(2, LocalDate.now().minusDays(5), LocalDate.now());
        List<Borrowed> borrows = new ArrayList<>();
        List<BorrowedDto> borrowedDtos = new ArrayList<>();
        borrows.add(borrowed1);
        borrows.add(borrowed2);
        borrowedDtos.add(borrowedDto1);
        borrowedDtos.add(borrowedDto2);

        when(borrowedMapper.mapToBorrowedDtoList(borrows)).thenReturn(borrowedDtos);
        when(dbService.findBorrowed()).thenReturn(borrows);

        //When
        List<BorrowedDto> findedBorrows = libraryFacade.findAllBorrows();

        //Then
        assertEquals(2, findedBorrows.size());
        assertEquals(LocalDate.now(), findedBorrows.get(0).getBorrowDate());
        assertEquals(LocalDate.now(), findedBorrows.get(1).getReturnDate());
    }

    @Test
    public void testReturnBook() throws BorrowNotFoundException {
        //Given
        Borrowed borrowed = new Borrowed(1, LocalDate.now(), null);
        BorrowedDto borrowedDto = new BorrowedDto(1, LocalDate.now(), null);

        when(dbService.findBorrowById(1)).thenReturn(Optional.ofNullable(borrowed));
        when(borrowedMapper.mapToBorrowedDto(borrowed)).thenReturn(borrowedDto);

        //When
        BorrowedDto returnBook = libraryFacade.returnBook(1);

        //Then
        assertEquals(LocalDate.now(), returnBook.getReturnDate());
    }
}
