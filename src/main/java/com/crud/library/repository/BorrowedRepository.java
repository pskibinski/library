package com.crud.library.repository;

import com.crud.library.domain.Borrowed;
import com.crud.library.domain.CopyOfTheBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BorrowedRepository extends CrudRepository<Borrowed, Integer> {

    List<Borrowed> findByCopyOfTheBookAndReturnDate(CopyOfTheBook copyOfTheBook, LocalDate returnDate);

    @Override
    List<Borrowed> findAll();

    @Override
    Borrowed save(Borrowed borrowed);

    @Override
    void delete(Borrowed borrowed);

    Optional<Borrowed> findById(int id);
}
