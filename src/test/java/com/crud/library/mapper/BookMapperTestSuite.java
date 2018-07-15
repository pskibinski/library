package com.crud.library.mapper;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookMapperTestSuite {

    @Autowired
    BookMapper bookMapper;

    @Test
    public void testMapToBookDto() throws BookNotFoundException {
        //Given
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);

        //When
        BookDto bookDto = bookMapper.mapToBookDto(book);

        //Then
        assertEquals("Frank Herbert", bookDto.getAuthor());
    }

    @Test
    public void testMapToBook() {
        //Given
        BookDto bookDto = new BookDto(1, "Frank Herbert", "Dune", 1965);

        //When
        Book book = bookMapper.mapToBook(bookDto);

        //Then
        assertEquals("Dune", book.getTitle());
        assertEquals(1965, book.getYear());
    }

    @Test
    public void testMapToBookDtoList() {
        //Given
        Book book1 = new Book(1, "Frank Herbert", "Dune", 1965);
        Book book2 = new Book(2, "Richard Morgan", "Altered Carbon", 2002);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        //When
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(books);

        //Then
        assertEquals(2, bookDtos.size());
        assertEquals(1965, bookDtos.get(0).getYear());
        assertEquals("Altered Carbon", bookDtos.get(1).getTitle());
    }
}
