package com.interblocks.iwallet.smb.util.jpy;

public enum JustPayStatusEnum {
    REGISTERED(11),
    CONSENT_ADDED(21),
    CONSENT_ADDING_FAILED(25),
    RANDOM_VERIFIED(31),
    RANDOM_VERIFY_FAILED(35),
    UNDEFINED(90),
    DELETED(99);


    private int value;
    private JustPayStatusEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static JustPayStatusEnum getByValue(int code) {
        for (JustPayStatusEnum e : values()) {
            if (e.value==code) return e;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
