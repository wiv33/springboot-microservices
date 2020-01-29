package com.psawesome.gamification.repository;

import com.psawesome.gamification.domain.LeaderBoardRow;
import com.psawesome.gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * package: com.psawesome.gamification.repository
 * author: PS
 * DATE: 2020-01-27 월요일 21:36
 */
public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {

    /**
     * ScoreCard의 점수를 합해서 사용자의 총 점수를 조회
     *
     * @param userId
     *         총 점수를 조회하고자 하는 사용자의 ID
     * @return 사용자의 총 점수
     */
    @Query("SELECT SUM(s.score) " +
            "FROM ScoreCard AS s " +
            "WHERE s.userId = :userId GROUP BY s.userId")
    Optional<Integer> getTotalScoreForUser(@Param("userId") final Long userId);

    /**
     * 사용자와 사용자의 총 점수를 나타내는 {@link LeaderBoardRow} 리스트를 조회
     *
     * @return 높은 점수순으로 정렬된 리더보드
     */
    @Query("SELECT NEW com.psawesome.gamification.domain.LeaderBoardRow(s.userId, SUM(s.score))" +
            "FROM ScoreCard s " +
            "GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();

    /**
     * 사용자의 모든 ScoreCard를 조회
     *
     * @param userId
     *         사용자 ID
     * @return 특정 사용자의 최근순으로 정렬된 ScoreCard 리스트
     */
    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);
}
