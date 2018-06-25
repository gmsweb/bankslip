/*
 * @(#)AbstractDeserializer.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Classe abstrata que disponibiliza métodos comuns para a deserialização de objetos.
 *
 * @Version 1.0.0 19/06/2018
 **/
public abstract class AbstractDeserializer<T> extends JsonDeserializer<T> {

    private static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    /** {@inheritDoc} **/
    @Override
    public final T deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
        final ObjectCodec oc = jsonParser.getCodec();
        final JsonNode node = oc.readTree(jsonParser);
        return deserialize(node);
    }

    /**
     * Método que deve ser invocado pelas implementações para deserializar conteudos JSON para objetos do tipo que a implementação
     * lida.
     *
     * @param node
     *         o conteudo JSON a ser deserializado
     * @return o objeto deserializado
     *
     * @throws IOException
     */
    protected abstract T deserialize(final JsonNode node);

    protected BigDecimal getNumberValue(final JsonNode node, final String fieldName) {
        return hasNonNullAndNonEmpty(node, fieldName) ? new BigDecimal(node.get(fieldName).asText()) : null;
    }

    protected String getFieldTextValue(final JsonNode node, final String fieldName) {
        return hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName).textValue() : null;
    }

    protected LocalDate getLocalDateValue(final JsonNode node, final String fieldName) {
        if (hasNonNullAndNonEmpty(node, fieldName)) {
            try {
                return LocalDate.parse(getFieldTextValue(node, fieldName), DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
            } catch (final DateTimeParseException exception) {
                throw new InvaliDateFormatException(
                        String.format("Invalid bankslip provided. The date field MUST be provided as %s", LOCAL_DATE_PATTERN));
            }
        }
        return null;
    }

    protected boolean has(final JsonNode node, final String fieldName) {
        return node.has(fieldName);
    }

    protected boolean hasNonNull(final JsonNode node, final String fieldName) {
        return has(node, fieldName) && node.hasNonNull(fieldName);
    }

    protected boolean hasNonNullAndNonEmpty(final JsonNode node, final String fieldName) {
        return hasNonNull(node, fieldName) && node.get(fieldName).asText().length() > 0;
    }
}
