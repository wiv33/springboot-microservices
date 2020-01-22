package com.psawesome.socialmultiplication.domain;

/**
 * package: com.psawesome.socialmultiplication.domain
 * author: PS
 * DATE: 2020-01-22 수요일 21:54
 */
public class Multiplication {

    private int factorA;
    private int factorB;

    private int result;


    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }

    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getResult() {
        return factorA * factorB;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result=" + factorA * factorB +
                '}';
    }
}
