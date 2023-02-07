package com.example.exception;

public class NoDataFoundException extends RuntimeException{

    public NoDataFoundException(Integer id) {
        super(String.format("Data con id %d no encontrado", id));
    }

    public NoDataFoundException() {
        super("Data no encontrada");
    }
}
