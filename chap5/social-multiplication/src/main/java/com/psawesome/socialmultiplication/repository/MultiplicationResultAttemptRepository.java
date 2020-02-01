package com.psawesome.socialmultiplication.repository;

import com.psawesome.socialmultiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * package: com.psawesome.socialmultiplication.repository
 * author: PS
 * DATE: 2020-01-26 일요일 15:55
 */
public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {

    /**
     *
     * @param userAlias
     * @return 닉네임에 해당하는 사용자의 최근 답안 5개
     */
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
