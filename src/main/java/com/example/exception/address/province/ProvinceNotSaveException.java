package com.example.exception.address.province;

public class ProvinceNotSaveException extends RuntimeException{

    private final Object data;
    public ProvinceNotSaveException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public ProvinceNotSaveException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
