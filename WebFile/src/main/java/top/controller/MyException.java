package top.controller;

public class MyException extends RuntimeException{
    private String message;

    public MyException(String mes){
        this.message = mes;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
