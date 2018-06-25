/*
 * @(#)BankslipStatusWrapper.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi;

import com.enterprise.bankslip.domain.BankslipStatus;
import com.enterprise.bankslip.restapi.serializers.BankslipStatusWrapperDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <Class coments go here>
 *
 * @Version 1.0.0 24/06/2018
 **/
@JsonDeserialize(using = BankslipStatusWrapperDeserializer.class)
@AllArgsConstructor
@Getter
public class BankslipStatusWrapper {

    private final BankslipStatus status;

    public String getStatusMessage() {
        return status.getMessage();
    }
}
