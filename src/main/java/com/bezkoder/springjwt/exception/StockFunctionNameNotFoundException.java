package com.bezkoder.springjwt.exception;

public class StockFunctionNameNotFoundException extends RuntimeException{
    public StockFunctionNameNotFoundException(String name){
        super("Could not found the stock function with name: "+ name);
    }
    
    public StockFunctionNameNotFoundException(Long id){
        super("Could not found the stock function with id: "+ id);
    }
}
