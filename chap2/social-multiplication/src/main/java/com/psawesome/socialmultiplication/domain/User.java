package com.psawesome.socialmultiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * package: com.psawesome.socialmultiplication.domain
 * author: PS
 * DATE: 2020-01-22 수요일 23:07
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class User {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    public final String alias;

    protected User() {
        alias = null;
    }
}
