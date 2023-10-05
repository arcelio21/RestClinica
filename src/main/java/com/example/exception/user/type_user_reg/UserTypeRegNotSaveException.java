package com.example.exception.user.type_user_reg;

public class UserTypeRegNotSaveException extends RuntimeException{
    
    private final Object data;

    public UserTypeRegNotSaveException(String message, Object data){

        super(message);
        this.data = data;
    }

    public UserTypeRegNotSaveException(Object data){
        super("Not Saved, Data no valid");
        this.data = data;
    }

    public UserTypeRegNotSaveException(){
        super("Not Saved, Data no valid");
        this.data = null;
    }

    public Object getData() {
        return data;
    }
}
