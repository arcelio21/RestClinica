package com.example.exception.address.village;

public class VillageNotUpdateExcepction extends RuntimeException{

    private final Object data;

    public VillageNotUpdateExcepction(String message, Object data) {
        super(message);
        this.data=data;
    }

    public VillageNotUpdateExcepction(String message) {
        super(message);
        this.data=null;
    }

    public final Object getData() {
        return data;
    }
}
