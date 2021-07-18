package com.an1shthomas.vendingmachine.entity;

public enum Coin {
    NO_COIN(0),
    ONE_DOLLAR(1),
    FIVE_DOLLAR(5),
    TEN_DOLLAR(10),
    TWENTY_DOLLAR(20);

    private final int value;

    Coin(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
