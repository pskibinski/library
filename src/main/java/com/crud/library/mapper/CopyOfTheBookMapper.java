package com.crud.library.mapper;

import com.crud.library.domain.CopyOfTheBook;
import com.crud.library.domain.CopyOfTheBookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyOfTheBookMapper {
    public CopyOfTheBook mapToCopyOfTheBook(CopyOfTheBookDto copyOfTheBookDto) {
        return new CopyOfTheBook(copyOfTheBookDto.getId(), copyOfTheBookDto.getBook());
    }

    public CopyOfTheBookDto mapToCopyOfTheBookDto(CopyOfTheBook copyOfTheBook) {
        return new CopyOfTheBookDto(copyOfTheBook.getId(), copyOfTheBook.getBook());
    }

    public List<CopyOfTheBookDto> mapToCopyOfTheBookDtoList(List<CopyOfTheBook> copyList) {
        return copyList.stream().
                map(c -> new CopyOfTheBookDto(c.getId(), c.getBook())).
                collect(Collectors.toList());
    }
}
