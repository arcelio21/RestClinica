package com.example.exception.address.village;

public class VillageNotSaveException extends RuntimeException{

    private final Object data;
    public VillageNotSaveException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public VillageNotSaveException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
