package com.psawesome.socialmultiplication.domain;

import lombok.*;

/**
 * package: com.psawesome.socialmultiplication.domain
 * author: PS
 * DATE: 2020-01-22 수요일 21:54
 */
@EqualsAndHashCode
@Getter
@ToString
@RequiredArgsConstructor
public final class Multiplication {

    private final int factorA;
    private final int factorB;

    public Multiplication() {
        this(0, 0);
    }
}
