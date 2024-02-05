package br.com.carolinefeitosa.desafiotodolist.exception;

public class BadRequestException extends RuntimeException {
    
    public BadRequestException(String message) {
        super(message);
    }
}
