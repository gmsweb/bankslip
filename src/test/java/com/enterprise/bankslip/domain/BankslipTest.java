/*
 * @(#)BankslipTest.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import com.enterprise.bankslip.domain.calculators.DefaultCalculator;
import com.enterprise.bankslip.domain.calculators.FineCalculator;
import com.enterprise.bankslip.domain.calculators.OverTimeCalculator;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Classe de testes para a classe <code>Bankslip</code>
 *
 * @author Glauber Shibata
 * @Version 1.0.0 25/06/2018
 **/
public class BankslipTest {

    private List<FineCalculator> calculators = new ArrayList<>();

    public BankslipTest() {
        calculators.add(new DefaultCalculator());
        calculators.add(new OverTimeCalculator());
    }

    @Test
    public void getFineWithMoreThanTenDaysLate() {
        final Bankslip bankslip = new Bankslip(LocalDate.of(2018, 1, 10), "Customer", BigDecimal.valueOf(100));
        final BigDecimal calculatedFine = bankslip.getFine(calculators, LocalDate.of(2018, 2, 10));
        assertEquals("O valor dos juros não corresponde", BigDecimal.valueOf(31), calculatedFine);
    }

    @Test
    public void getFineUpToTenDaysLate() {
        final Bankslip bankslip = new Bankslip(LocalDate.of(2018, 1, 10), "Customer", BigDecimal.valueOf(100));
        final BigDecimal calculatedFine = bankslip.getFine(calculators, LocalDate.of(2018, 1, 15));
        assertEquals("O valor dos juros não corresponde", BigDecimal.valueOf(2.5), calculatedFine);
    }

    @Test
    public void getFine() {
        final Bankslip bankslip = new Bankslip(LocalDate.of(2018, 1, 10), "Customer", BigDecimal.valueOf(100));
        final BigDecimal calculatedFine = bankslip.getFine(calculators, LocalDate.of(2018, 1, 9));
        assertEquals("O valor dos juros não corresponde", BigDecimal.ZERO, calculatedFine);
    }

}
