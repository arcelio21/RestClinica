package com.example.exception.address;

public class AddressNotUpdateException extends RuntimeException{

    private final Object data;

    public AddressNotUpdateException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public AddressNotUpdateException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
