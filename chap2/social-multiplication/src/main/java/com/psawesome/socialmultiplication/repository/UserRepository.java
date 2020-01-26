package com.psawesome.socialmultiplication.repository;

import com.psawesome.socialmultiplication.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * package: com.psawesome.socialmultiplication.repository
 * author: PS
 * DATE: 2020-01-26 일요일 15:58
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAlias(final String alias);
}
