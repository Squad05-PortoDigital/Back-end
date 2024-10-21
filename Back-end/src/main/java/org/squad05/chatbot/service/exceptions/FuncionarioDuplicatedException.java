package org.squad05.chatbot.service.exceptions;

public class FuncionarioDuplicatedException extends RuntimeException {
    public FuncionarioDuplicatedException(String message) {
        super(message);
    }
}
