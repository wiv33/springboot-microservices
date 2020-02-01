package com.psawesome.gamification.repository;

import com.psawesome.gamification.domain.BadgeCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * package: com.psawesome.gamification.repository
 * author: PS
 * DATE: 2020-01-27 월요일 21:31
 */
public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long> {

    /**
     * @param userId
     *         BadgeCard를 조회하고자 하는 사용자의 ID
     * @return List 최근 획득한 순으로 정렬된 BadgeCard 리스트
     */
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
