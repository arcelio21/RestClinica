package com.example.exception.address.village;

public class VillageNotUpdateException extends RuntimeException{

    private final Object data;

    public VillageNotUpdateException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public VillageNotUpdateException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
