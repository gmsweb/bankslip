/*
 * @(#)SpringContextConfiguration.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.config;

import com.enterprise.bankslip.domain.calculators.DefaultCalculator;
import com.enterprise.bankslip.domain.calculators.FineCalculator;
import com.enterprise.bankslip.domain.calculators.OverTimeCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe de configuração do contexto do Spring
 *
 * @Version 1.0.0 19/06/2018
 **/
@Configuration
@ComponentScan(basePackages = {"com.contaazul.bankslip"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.contaazul.bankslip.config"}))
public class SpringContextConfiguration {

    @Bean
    public List<FineCalculator> fineCalculators() {
        final List<FineCalculator> calculators = new ArrayList<>(2);
        calculators.add(new DefaultCalculator());
        calculators.add(new OverTimeCalculator());
        return Collections.unmodifiableList(calculators);
    }
}
