package com.crud.library.mapper;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.CopyOfTheBookDto;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyOfTheBookMapper {

    @Autowired
    private DbService dbService;

    public CopyOfTheBook mapToCopyOfTheBook(CopyOfTheBookDto copyOfTheBookDto) throws BookNotFoundException {
        return new CopyOfTheBook(copyOfTheBookDto.getId(), dbService.findBookById(copyOfTheBookDto.getBookId()).orElseThrow(BookNotFoundException::new));
    }

    public CopyOfTheBookDto mapToCopyOfTheBookDto(CopyOfTheBook copyOfTheBook) {
        return new CopyOfTheBookDto(copyOfTheBook.getId(), copyOfTheBook.getBook().getId());
    }

    public List<CopyOfTheBookDto> mapToCopyOfTheBookDtoList(List<CopyOfTheBook> copyList) {
        return copyList.stream().
                map(c -> new CopyOfTheBookDto(c.getId(), c.getBook().getId())).
                collect(Collectors.toList());
    }
}
