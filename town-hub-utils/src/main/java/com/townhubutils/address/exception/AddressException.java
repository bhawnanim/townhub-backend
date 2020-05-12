package com.townhubutils.address.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AddressException {

    private static Logger logger = LoggerFactory.getLogger(AddressException.class);

    @ExceptionHandler(value = {Exception.class})
    private ResponseEntity<Map<String, Object>> AddressException(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("error id", ex.getMessage().hashCode());
        map.put("message", ex.getCause().getMessage());
        logger.error(ex.getMessage().hashCode() + " - " + ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
