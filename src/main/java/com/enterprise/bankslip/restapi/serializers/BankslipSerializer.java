/*
 * @(#)BankslipSerializer.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import com.enterprise.bankslip.domain.Bankslip;
import com.enterprise.bankslip.domain.calculators.FineCalculator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <Class coments go here>
 *
 * @Version 1.0.0 19/06/2018
 **/
@AllArgsConstructor
@Component
public class BankslipSerializer extends JsonSerializer<Bankslip> {

    private final List<FineCalculator> fineCalculators;

    @Override
    public void serialize(final Bankslip bankslip, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("code", bankslip.getCode());
        jsonGenerator.writeStringField("due_date", bankslip.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        jsonGenerator.writeStringField("total_in_cents", bankslip.getTotal().toString());
        jsonGenerator.writeStringField("customer", bankslip.getCustomer());
        jsonGenerator.writeStringField("fine", bankslip.getFine(fineCalculators, LocalDate.now()).toString());
        jsonGenerator.writeEndObject();

    }
}
