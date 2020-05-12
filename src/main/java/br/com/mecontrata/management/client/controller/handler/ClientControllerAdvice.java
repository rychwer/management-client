package br.com.mecontrata.management.client.controller.handler;

import br.com.mecontrata.management.client.facade.exception.ClientAlreadyExistsException;
import br.com.mecontrata.management.client.facade.exception.ClientNotFoundException;
import br.com.mecontrata.management.client.facade.exception.EmailNotConfirmedException;
import br.com.server.resource.handle.DefaultControllerAdvice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClientControllerAdvice extends DefaultControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientAlreadyExistsException.class)
    @ResponseBody
    public Map<String, Object> handleClientAlreadyExistsExceptions(ClientAlreadyExistsException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put(messageSource.getMessage("status.code", null, null), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put(messageSource.getMessage("message", null, null), ex.getLocalizedMessage());
        errors.put(messageSource.getMessage("message.code", null, null), 1);
        log.error(ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseBody
    public Map<String, Object> handleClientNotFoundExceptions(ClientNotFoundException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put(messageSource.getMessage("status.code", null, null), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put(messageSource.getMessage("message", null, null), ex.getLocalizedMessage());
        errors.put(messageSource.getMessage("message.code", null, null), 2);
        log.error(ex.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNotConfirmedException.class)
    @ResponseBody
    public Map<String, Object> handleEmailNotConfirmedExceptions(EmailNotConfirmedException ex) {
        Map<String, Object> errors = new HashMap<>();
        errors.put(messageSource.getMessage("status.code", null, null), String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put(messageSource.getMessage("message", null, null), ex.getMessage());
        errors.put(messageSource.getMessage("message.code", null, null), 3);
        log.error(ex.getLocalizedMessage());
        return errors;
    }

}
