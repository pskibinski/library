package com.crud.library.mapper;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.domain.Book;
import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.CopyOfTheBookDto;
import com.crud.library.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CopyOfTheBookMapperTestSuite {

    @Autowired
    CopyOfTheBookMapper copyOfTheBookMapper;

    @MockBean
    DbService dbService;

    @Test
    public void testCopyOfTheBookMapToDto() {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy = new CopyOfTheBook(1, book);

        //When
        CopyOfTheBookDto copyDto = copyOfTheBookMapper.mapToCopyOfTheBookDto(copy);

        //Then
        assertEquals(1, copyDto.getId());
        assertEquals(1, copyDto.getBookId());
    }

    @Test
    public void testDtoToCopyOfTheBook() throws BookNotFoundException {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBookDto copyDto = new CopyOfTheBookDto(1, 1);

        //When
        when(dbService.findBookById(1)).thenReturn(Optional.of(book));
        CopyOfTheBook copy = copyOfTheBookMapper.mapToCopyOfTheBook(copyDto);

        //Then
        assertEquals(1, copy.getId());
        assertEquals(book.getAuthor(), copy.getBook().getAuthor());
        assertEquals(book.getTitle(), copy.getBook().getTitle());
    }
}
