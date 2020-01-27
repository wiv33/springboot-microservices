package com.psawesome.socialmultiplication.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * package: com.psawesome.socialmultiplication.domain
 * author: PS
 * DATE: 2020-01-22 수요일 21:54
 */
@EqualsAndHashCode
@Getter
@ToString
@RequiredArgsConstructor
@Entity
public final class Multiplication {

    @Id
    @GeneratedValue
    @Column(name = "MULTIPLICATION_ID")
    private Long id;

    private final int factorA;
    private final int factorB;

    public Multiplication() {
        this(0, 0);
    }
}
