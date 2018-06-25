/*
 * @(#)InvalidBankslipStatusException.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada quando não é possível deserializar um {@link BankslipStatus} corretamente.
 *
 * @Version 1.0.0 24/06/2018
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidBankslipStatusException extends RuntimeException {

    public InvalidBankslipStatusException() {
        super("Invalid status");
    }

}
