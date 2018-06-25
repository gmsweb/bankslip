/*
 * @(#)BankslipNotFoundException.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada quando não é possível encontrar um objeto {@link Bankslip}
 *
 * @Version 1.0.0 23/06/2018
 **/
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BankslipNotFoundException extends RuntimeException {

    private static final String message = "Bankslip not found with the specified id: %s";

    public BankslipNotFoundException(final String code) {
        super(String.format(message, code));
    }
}
