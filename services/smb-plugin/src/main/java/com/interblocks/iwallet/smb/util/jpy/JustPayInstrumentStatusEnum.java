package com.interblocks.iwallet.smb.util.jpy;

public enum JustPayInstrumentStatusEnum {

    UNREGISTERED_PENDING(15),
    UNREGISTERED_NEW(10),
    REGISTERED_ACTIVE(21),
    REGISTERED_PENDING(25),
    REGISTERED_NEW(20);


    private int value;
    private JustPayInstrumentStatusEnum(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static JustPayInstrumentStatusEnum getByValue(int code) {
        for (JustPayInstrumentStatusEnum e : values()) {
            if (e.value==code) return e;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return this.name();
    }
}
