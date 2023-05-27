package com.example.exception.address.district;

public class DistrictNotSaveException extends RuntimeException{

    private final Object data;
    public DistrictNotSaveException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public DistrictNotSaveException(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
