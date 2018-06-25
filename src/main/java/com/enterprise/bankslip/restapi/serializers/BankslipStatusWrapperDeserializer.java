/*
 * @(#)BankslipStatusWrapperDeserializer.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import com.enterprise.bankslip.domain.BankslipStatus;
import com.enterprise.bankslip.domain.InvalidBankslipStatusException;
import com.enterprise.bankslip.restapi.BankslipStatusWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

/**
 * <Class coments go here>
 *
 * @Version 1.0.0 24/06/2018
 **/
@Component
public class BankslipStatusWrapperDeserializer extends AbstractDeserializer<BankslipStatusWrapper> {

    @Override
    protected BankslipStatusWrapper deserialize(final JsonNode node) {
        try {
            final BankslipStatus status = BankslipStatus.valueOf(getFieldTextValue(node, "status").toUpperCase());
            return new BankslipStatusWrapper(status);
        } catch (final IllegalArgumentException exception) {
            throw new InvalidBankslipStatusException();
        }
    }
}
