/*
 * @(#)AbstractFineCalculator.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain.calculators;

import com.enterprise.bankslip.domain.Bankslip;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A classe <code>AbstractFineCalculator</code> contem métodos comuns aos calculadores de juros
 *
 * @Version 1.0.0 19/06/2018
 **/
public abstract class AbstractFineCalculator implements FineCalculator {

    protected static final long TEND_DAYS = 10l;

    /** {@inheritDoc} **/
    @Override
    public BigDecimal calculate(final Bankslip bankslip, final LocalDate referenceDate) {
        return getFine(bankslip.getTotal(), bankslip.getOverdueDays(referenceDate));
    }

    private BigDecimal getFine(final BigDecimal value, final long overdueDates) {
        return new BigDecimal(value.doubleValue() * getInterestRate() * overdueDates);
    }

    protected abstract double getInterestRate();

}
