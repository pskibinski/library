package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    @Override
    Book save(Book book);

    @Override
    void delete(Book book);

    Optional<Book> findById(int id);

    @Override
    List<Book> findAll();
}
