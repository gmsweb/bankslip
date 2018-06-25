/*
 * @(#)BankslipNotProvidedException.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada quando não foram encontrados dados de um boleto em uma request.
 *
 * @Version 1.0.0 23/06/2018
 **/
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BankslipNotProvidedException extends RuntimeException {

    public BankslipNotProvidedException() {
        super("Bankslip not provided in the request body");
    }

}
