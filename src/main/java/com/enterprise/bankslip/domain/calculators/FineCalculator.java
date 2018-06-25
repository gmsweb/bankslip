/*
 * @(#)FineCalculator.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain.calculators;

import com.enterprise.bankslip.domain.Bankslip;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Interface que define métodos de cálculo de juros dos boletos.
 *
 * @author Glauber Shibata
 * @Version 1.0.0 18/06/2018
 **/
public interface FineCalculator {

    /**
     * Método que identifica se determinada implementação do calculador é responsável por realizar o cálculo.
     *
     * @param bankslip
     *         o objeto {@link Bankslip}
     * @param referenceDate
     *         a data de referência para cáculo dos juros
     * @return
     */
    boolean shouldCalculate(final Bankslip bankslip, final LocalDate referenceDate);

    /**
     * Método que calcula o valor dos juros do boleto com base na data de referência.
     *
     * @param bankslip
     *         o objeto {@link Bankslip}
     * @param referenceDate
     *         data de referência para cáculo dos juros
     * @return
     */
    BigDecimal calculate(final Bankslip bankslip, final LocalDate referenceDate);

}
