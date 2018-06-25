/*
 * @(#)ListWrapperSerializer.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import com.enterprise.bankslip.config.SerializationBeans;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * A classe {@link ListWrapperSerializer} e responsavel por serializar uma lista de elementos. Para ser utilizada, e necessario
 * que o serializer do tipo genérico seja cadastrado na lista de serializadores que encontra-se definido na classe {@link
 * SerializationBeans}
 *
 * @Version 1.0.0 22/06/2018
 **/
@Component
@AllArgsConstructor
public class ListWrapperSerializer<T> extends JsonSerializer<ListWrapper<T>> {

    private final Map<Class, JsonSerializer> serializers;

    @Override
    public void serialize(final ListWrapper<T> wrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (final T element : wrapper.getObjects()) {
            serializers.get(element.getClass()).serialize(element, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndArray();
    }
}
