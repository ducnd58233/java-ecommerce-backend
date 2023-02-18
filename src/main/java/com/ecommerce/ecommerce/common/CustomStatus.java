package com.ecommerce.ecommerce.common;

public enum CustomStatus {
    ACTIVE(0),
    INACTIVE(1),
    BANNED(2),
    BLOCKED(3);

    private final int value;
    private CustomStatus(int value) {
        this.value = value;
    };

    public int getValue() {
        return value;
    }
}
