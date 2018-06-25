/*
 * @(#)BankslipStatus.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import lombok.Getter;

/**
 * A enumeração <code>BankslipStatus</code> representa os possíveis status de um boleto.
 *
 * @Version 1.0.0 18/06/2018
 **/
public enum BankslipStatus {

    PENDING(null),
    PAID("Bankslip paid"),
    CANCELED("Bankslip canceled");

    @Getter
    private String message;

    BankslipStatus(final String message) {
        this.message = message;
    }
}
