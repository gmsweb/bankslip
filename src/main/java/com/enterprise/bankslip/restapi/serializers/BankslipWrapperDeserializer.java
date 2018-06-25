/*
 * @(#)BankslipWrapperDeserializer.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import com.enterprise.bankslip.domain.Bankslip;
import com.enterprise.bankslip.domain.BankslipNotProvidedException;
import com.enterprise.bankslip.restapi.BankslipWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A classe <code>BankslipWrapperDeserializer</code> é responsável pela deserialização de conteúdos JSON para objetos {@link
 * BankslipWrapper}
 *
 * @Version 1.0.0 19/06/2018
 **/
@Component
public class BankslipWrapperDeserializer extends AbstractDeserializer<BankslipWrapper> {

    public static final String CUSTOMER = "customer";

    public static final String DUE_DATE = "due_date";

    public static final String TOTAL_IN_CENTS = "total_in_cents";

    /** {@inheritDoc} **/
    @Override
    public BankslipWrapper deserialize(final JsonNode node) {
        verifyProvidedData(node);
        final String customer = getFieldTextValue(node, CUSTOMER);
        final LocalDate dueDate = getLocalDateValue(node, DUE_DATE);
        final BigDecimal total = getNumberValue(node, TOTAL_IN_CENTS);

        return new BankslipWrapper(new Bankslip(dueDate, customer, total));
    }

    private void verifyProvidedData(final JsonNode node) {
        if (node.size() == 0) {
            throw new BankslipNotProvidedException();
        }
    }

}
