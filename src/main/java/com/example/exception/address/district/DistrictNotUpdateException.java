package com.example.exception.address.district;

public class DistrictNotUpdateException extends RuntimeException{

    private final Object data;

    public DistrictNotUpdateException(String message, Object data) {
        super(message);
        this.data=data;
    }

    public DistrictNotUpdateException(String message) {
        super(message);
        this.data=null;
    }

    public Object getData() {
        return data;
    }
}

