package com.example.exception.address.province;

public class ProvinceNotUpdateException extends RuntimeException{

    private final Object data;

    public ProvinceNotUpdateException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public ProvinceNotUpdateException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
