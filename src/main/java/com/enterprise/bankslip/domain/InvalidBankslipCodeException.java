/*
 * @(#)InvalidBankslipCodeException.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada quando o código informado do boleto é inválido.
 *
 * @Version 1.0.0 23/06/2018
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidBankslipCodeException extends RuntimeException {

    private static final String message = "Invalid id provided: [ %s ]. It must be a valid UUID";

    public InvalidBankslipCodeException(final String code) {
        super(String.format(message, code));
    }
}
