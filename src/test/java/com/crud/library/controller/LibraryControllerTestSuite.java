package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.facade.LibraryFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryController.class)
@RunWith(SpringRunner.class)
public class LibraryControllerTestSuite {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LibraryFacade libraryFacade;

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        UserDto user = new UserDto("John", "Smith");

        doNothing().when(libraryFacade).addUser(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(user);

        //When & Then
        mockMvc.perform(post("/v1/library/addUser").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());
        verify(libraryFacade, times(1)).addUser(any(UserDto.class));
        verifyNoMoreInteractions(libraryFacade);
    }

    @Test
    public void shouldFetchUserList() throws Exception {
        //Given
        UserDto user1 = new UserDto(1, "John", "Smith", LocalDate.now());
        UserDto user2 = new UserDto(2, "Harry", "Potter", LocalDate.now());
        List<UserDto> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(libraryFacade.findAllUsers()).thenReturn(users);

        //When & Then
        mockMvc.perform(get("/v1/library/getUsers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].firstName", is("Harry")));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        //Given
        doNothing().when(libraryFacade).deleteUser(1);

        //When & Then
        mockMvc.perform(delete("/v1/library/deleteUser?userId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(libraryFacade, times(1)).deleteUser(1);
        verifyNoMoreInteractions(libraryFacade);
    }

    @Test
    public void shouldCreateBook() throws Exception {
        BookDto book = new BookDto("Frank Herbert", "Dune", 1965);

        doNothing().when(libraryFacade).addBook(book);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(book);

        //When $$ Then
        mockMvc.perform(post("/v1/library/addBook").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());
        verify(libraryFacade, times(1)).addBook(any(BookDto.class));
        verifyNoMoreInteractions(libraryFacade);
    }

    @Test
    public void shouldFetchBookList() throws Exception {
        //Given
        BookDto book1 = new BookDto(1,"Frank Herbert", "Dune", 1965);
        BookDto book2 = new BookDto(2,"Richard Morgan", "Altered Carbon", 2002);
        List<BookDto> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);

        when(libraryFacade.findAllBooks()).thenReturn(list);

        //When & Then
        mockMvc.perform(get("/v1/library/getBooks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Altered Carbon")));
    }

    @Test
    public void shouldCreateCopy() throws Exception {
        //Givne
        CopyOfTheBookDto copy = new CopyOfTheBookDto(1);

        doNothing().when(libraryFacade).addCopy(copy, 1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(copy);

        //When & Then
        mockMvc.perform(post("/v1/library/addCopy?bookId=1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());
        verify(libraryFacade, times(1)).addCopy(any(CopyOfTheBookDto.class), any(Integer.class));
        verifyNoMoreInteractions(libraryFacade);
    }

    @Test
    public void shouldFetchCopyList() throws Exception {
        //Given
        CopyOfTheBookDto copy1 = new CopyOfTheBookDto(1,1);
        CopyOfTheBookDto copy2 = new CopyOfTheBookDto(2,2);
        List<CopyOfTheBookDto> list = new ArrayList<>();
        list.add(copy1);
        list.add(copy2);

        when(libraryFacade.findAllCopies()).thenReturn(list);

        //When & Then
        mockMvc.perform(get("/v1/library/getCopies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].bookId", is(1)))
                .andExpect(jsonPath("$[1].bookId", is(2)));
    }

    @Test
    public void shouldFetchAvailableCopiesOfTheBook() throws Exception {
        //Given
        CopyOfTheBookDto copy1 = new CopyOfTheBookDto(1,1);
        CopyOfTheBookDto copy2 = new CopyOfTheBookDto(2,2);
        List<CopyOfTheBookDto> list = new ArrayList<>();
        list.add(copy1);
        list.add(copy2);

        when(libraryFacade.findAllAvailableCopies(1)).thenReturn(list);

        //When & Then
        mockMvc.perform(get("/v1/library/getAvailableCopies?bookId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].bookId", is(2)));
    }

    @Test
    public void shoudlReturnCopyStatus() throws Exception {
        //Given
        when(libraryFacade.checkCopyStatus(1)).thenReturn(true);

        //When & Then
        mockMvc.perform(get("/v1/library/checkStatus?copyId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(libraryFacade, times(1)).checkCopyStatus(1);
        verifyNoMoreInteractions(libraryFacade);
    }

    @Test
    public void shouldCreateBorrowBook() throws Exception {
        //Given
        BorrowedDto borrow = new BorrowedDto();

        doNothing().when(libraryFacade).borrowBook(borrow, 1, 1);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(borrow);

        //When & Then
        mockMvc.perform(post("/v1/library/borrowBook?userId=1&copyId=1").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());
        verify(libraryFacade, times(1)).borrowBook(any(BorrowedDto.class), any(Integer.class), any(Integer.class));
        verifyNoMoreInteractions(libraryFacade);
    }

    @Test
    public void shouldFetchBorrowList() throws Exception {
        //Given
        BorrowedDto borrow = new BorrowedDto(1, LocalDate.of(2018, 07, 14), null, 1, 1);
        List<BorrowedDto> list = new ArrayList<>();
        list.add(borrow);

        when(libraryFacade.findAllBorrows()).thenReturn(list);

        //When & Then
        mockMvc.perform(get("/v1/library/getBorrows").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].borrowDate", is("2018-07-14")));
    }

    @Test
    public void testReturnBook() throws Exception {
        //Given
        BorrowedDto borrow = new BorrowedDto(
                1,
                LocalDate.of(2018, 06, 14),
                null,
                1,
                1);
        BorrowedDto returned = new BorrowedDto(1,
                LocalDate.of(2018, 06, 14),
                LocalDate.of(2018, 07, 14),
                1,
                1);

        when(libraryFacade.returnBook(1)).thenReturn(returned);

        //When & Then
        mockMvc.perform(put("/v1/library/returnBook?borrowId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.returnDate", is("2018-07-14")));
    }

}
