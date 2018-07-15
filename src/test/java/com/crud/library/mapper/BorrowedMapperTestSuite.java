package com.crud.library.mapper;

import com.crud.library.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BorrowedMapperTestSuite {

    @Autowired
    BorrowedMapper borrowedMapper;

    @Test
    public void testMapToBorrowedDto() {
        //Given
        User user = new User(1, "John", "Smith", LocalDate.now().minusDays(5));
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy = new CopyOfTheBook(1, book);
        Borrowed borrowed = new Borrowed(1, LocalDate.now(), null, user, copy);

        //When
        BorrowedDto borrowedDto = borrowedMapper.mapToBorrowedDto(borrowed);

        //Then
        assertEquals(1, borrowedDto.getId());
        assertEquals(LocalDate.now(), borrowedDto.getBorrowDate());
        assertNull(borrowedDto.getReturnDate());
    }

    @Test
    public void testMapToBorrowed() {
        //Given
        BorrowedDto borrowedDto = new BorrowedDto(1, LocalDate.now().minusDays(5), LocalDate.now());

        //When
        Borrowed borrowed = borrowedMapper.mapToBorrowed(borrowedDto);

        //Then
        assertEquals(1, borrowed.getId());
        assertEquals(LocalDate.now().minusDays(5), borrowed.getBorrowDate());
        assertEquals(LocalDate.now(), borrowed.getReturnDate());
    }

    @Test
    public void testMapToBorrowedDtoList() {
        //Given
        User user1 = new User(1, "John", "Smith", LocalDate.now().minusDays(5));
        User user2 = new User(1, "Harry", "Potter", LocalDate.now().minusDays(10));
        Book book = new Book(1, "Frank Herbert", "Dune", 1965);
        CopyOfTheBook copy1 = new CopyOfTheBook(1, book);
        CopyOfTheBook copy2 = new CopyOfTheBook(2, book);
        Borrowed borrowed1 = new Borrowed(1, LocalDate.now(), null, user1, copy1);
        Borrowed borrowed2 = new Borrowed(2, LocalDate.now(), null, user2, copy2);
        List<Borrowed> list = new ArrayList<>();
        list.add(borrowed1);
        list.add(borrowed2);

        //When
        List<BorrowedDto> borrowedDtoList = borrowedMapper.mapToBorrowedDtoList(list);

        //Then
        assertEquals(2, borrowedDtoList.size());
        assertEquals(1, borrowedDtoList.get(0).getId());
        assertEquals(LocalDate.now(), borrowedDtoList.get(0).getBorrowDate());
    }
}
