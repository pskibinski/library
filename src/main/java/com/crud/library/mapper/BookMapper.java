package com.crud.library.mapper;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.service.DbService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
public class BookMapper {

    private DbService dbService;

    public Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.getAuthor(), bookDto.getTitle(), bookDto.getYear());
    }

    public BookDto mapToBookDto(Book book) throws BookNotFoundException {
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getYear(),
                book.getCopiesOfBook().stream().map(c -> c.getId()).collect(Collectors.toList()));
    }

    public List<BookDto> mapToBookDtoList(List<Book> bookList) {
        return bookList.stream().
                map(b -> new BookDto(b.getId(), b.getAuthor(), b.getTitle(), b.getYear(),
                        b.getCopiesOfBook().stream()
                                .map(c -> c.getId()).collect(Collectors.toList())))
                        .collect(Collectors.toList());
    }
}