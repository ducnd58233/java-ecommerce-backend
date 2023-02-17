package com.ecommerce.ecommerce.common;

public enum Status {
    ACTIVE(0),
    INACTIVE(1),
    BLOCKED(2);

    private final int value;
    private Status(int value) {
        this.value = value;
    };

    public int getValue() {
        return value;
    }
}
