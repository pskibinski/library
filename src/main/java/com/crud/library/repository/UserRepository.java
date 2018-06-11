package com.crud.library.repository;

import com.crud.library.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    @Override
    User save(User user);

    @Override
    void delete(User user);

    Optional<User> findUserById(int id);

    @Override
    List<User> findAll();
}
