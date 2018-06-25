/*
 * @(#)RestControllerExceptionHandler.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

/**
 * A classe <code>RestControllerExceptionHandler</code> é responsável pelo tratamento das exceções lançadas pelo sistema.
 *
 * @Version 1.0.0 23/06/2018
 **/
@ControllerAdvice
@SuppressWarnings("unused")
public class RestControllerExceptionHandler {

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(final Exception exception) {
        final Throwable cause = getCause(exception);
        if (cause.getClass().isAnnotationPresent(ResponseStatus.class)) {
            final HttpStatus status = getHttpStatus(cause.getClass());
            return new ResponseEntity<>(new Response(exception.getMessage()), status);
        }
        return new ResponseEntity<>(new Response("It was not possible to complete your request."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Método responsável por tratar especificamente de {@link ConstraintViolationException}
     *
     * @param exception
     *         a {@link ConstraintViolationException} que foi lançada
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Response> handleConstraintViolationException(final ConstraintViolationException exception) {
        return new ResponseEntity<>(new Response("Invalid bankslip provided. The possible reasons are: "
                + "A field of the provided bankslip was null or with invalid values"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private HttpStatus getHttpStatus(final Class<? extends Throwable> clazz) {
        final ResponseStatus responseStatus = clazz.getAnnotation(ResponseStatus.class);
        return HttpStatus.INTERNAL_SERVER_ERROR == responseStatus.value() ? responseStatus.code() : responseStatus.value();
    }

    private Throwable getCause(final Exception exception) {
        return exception.getCause() != null ? exception.getCause() : exception;
    }
}
