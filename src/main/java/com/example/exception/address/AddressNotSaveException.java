package com.example.exception.address;

public class AddressNotSaveException extends RuntimeException{

    private final Object data;
    public AddressNotSaveException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public AddressNotSaveException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
