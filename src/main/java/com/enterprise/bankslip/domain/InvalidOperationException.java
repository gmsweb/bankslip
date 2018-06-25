/*
 * @(#)InvalidOperationException.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma operação infringi alguma regra de negócio.
 *
 * @Version 1.0.0 24/06/2018
 **/
@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException(final String message) {
        super(message);
    }
}
