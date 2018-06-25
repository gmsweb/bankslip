/*
 * @(#)SerializationBeans.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.config;

import com.enterprise.bankslip.domain.Bankslip;
import com.enterprise.bankslip.restapi.serializers.BankslipSerializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de configuração que contem o mapa de serializadores do sistema.
 *
 * @Version 1.0.0 22/06/2018
 **/
@Configuration
@AllArgsConstructor
public class SerializationBeans {

    private final BankslipSerializer bankslipSerializer;

    @Bean
    public Map<Class, JsonSerializer> serializersMap() {
        final Map<Class, JsonSerializer> serializers = new HashMap<>(1);
        serializers.put(Bankslip.class, bankslipSerializer);
        return serializers;
    }

}
