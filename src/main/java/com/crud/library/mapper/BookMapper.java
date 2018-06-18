package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getAuthor(), bookDto.getTitle(), bookDto.getYear(), bookDto.getCopiesOfBook());
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto(book.getId(), book.getAuthor(), book.getTitle(), book.getYear());
    }

    public List<BookDto> mapToBookDtoList(List<Book> bookList) {
        return bookList.stream().
                map(b -> new BookDto(b.getId(), b.getAuthor(), b.getTitle(), b.getYear(), b.getCopiesOfBook())).
                collect(Collectors.toList());
    }
}
