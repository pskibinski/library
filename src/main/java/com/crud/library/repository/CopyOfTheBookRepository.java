package com.crud.library.repository;

import com.crud.library.domain.CopyOfTheBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CopyOfTheBookRepository extends CrudRepository<CopyOfTheBook, Integer> {

    @Override
    CopyOfTheBook save(CopyOfTheBook copyOfTheBook);

    @Override
    void delete(CopyOfTheBook copyOfTheBook);

    Optional<CopyOfTheBook> findById(int id);

    @Override
    List<CopyOfTheBook> findAll();

    List<CopyOfTheBook> findAllByBook_Id(int id);
}
