package br.com.mecontrata.management.client.facade.exception;

public class EmailNotConfirmedException extends RuntimeException {

    public EmailNotConfirmedException(String message) {
        super(message);
    }

}
