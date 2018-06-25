/*
 * @(#)InvaliDateFormatException.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada quando não é possível parsear uma {@link String} para um objeto {@link java.time.LocalDate} válido.
 *
 * @Version 1.0.0 23/06/2018
 **/
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvaliDateFormatException extends RuntimeException {

    public InvaliDateFormatException(final String message) {
        super(message);
    }
}
