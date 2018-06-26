package com.crud.library.mapper;

import com.crud.library.domain.Borrowed;
import com.crud.library.domain.BorrowedDto;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BorrowedMapper {

    public Borrowed mapToBorrowed(BorrowedDto borrowedDto) {
        return new Borrowed(borrowedDto.getId(), borrowedDto.getBorrowDate(), borrowedDto.getReturnDate());
    }

    public BorrowedDto mapToBorrowedDto(Borrowed borrowed) {
        return new BorrowedDto(borrowed.getId(),borrowed.getBorrowDate(), borrowed.getReturnDate(), borrowed.getCopyOfTheBook().getId(), borrowed.getUser().getId());
    }

    public List<BorrowedDto> mapToBorrowedDtoList(List<Borrowed> borrowedList) {
        return borrowedList.stream().
                map(b -> new BorrowedDto(b.getId(), b.getCopyOfTheBook().getId(), b.getUser().getId())).
                collect(Collectors.toList());
    }
}
