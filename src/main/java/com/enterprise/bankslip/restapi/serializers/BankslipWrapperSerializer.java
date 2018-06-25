/*
 * @(#)BankslipWrapperSerializer.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import com.enterprise.bankslip.restapi.BankslipWrapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <Class coments go here>
 *
 * @Version 1.0.0 23/06/2018
 **/
@Component
@AllArgsConstructor
public class BankslipWrapperSerializer extends JsonSerializer<BankslipWrapper> {

    private final BankslipSerializer bankslipSerializer;

    @Override
    public void serialize(final BankslipWrapper bankslipWrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider) throws IOException {
        bankslipSerializer.serialize(bankslipWrapper.getBankslip(), jsonGenerator, serializerProvider);
    }
}
