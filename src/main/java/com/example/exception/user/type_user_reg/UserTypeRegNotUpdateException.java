package com.example.exception.user.type_user_reg;

public class UserTypeRegNotUpdateException extends RuntimeException{
    
    private final Object data;

    public UserTypeRegNotUpdateException(String message, Object data){

        super(message);
        this.data = data;
    }

    public UserTypeRegNotUpdateException(Object data){
        super("Not Saved, Data no valid");
        this.data = data;
    }

    public UserTypeRegNotUpdateException(){
        super("Not Saved, Data no valid");
        this.data = null;
    }

    public Object getData() {
        return data;
    }

    
}
