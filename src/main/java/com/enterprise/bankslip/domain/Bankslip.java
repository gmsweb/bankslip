/*
 * @(#)Bankslip.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import com.enterprise.bankslip.domain.calculators.FineCalculator;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = {"code"})
@ToString(of = {"code", "dueDate"})
@Table(name = "BANKSLIP")
public class Bankslip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private final Long id;

    @NotNull(message = "O código do boleto não pode ser nulo.")
    @Column(name = "CODE", length = 50)
    private final String code;

    @NotNull(message = "A data do boleto não pode ser nula.")
    @Column(name = "DUE_DATE")
    private final LocalDate dueDate;

    @NotNull(message = "O status do boleto não pode ser nulo.")
    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private BankslipStatus status;

    @NotNull(message = "O nome do cliente não pode ser nulo.")
    @Column(name = "CUSTOMER", length = 300)
    private final String customer;

    @NotNull(message = "O valor do boleto não pode ser nulo.")
    @Positive(message = "O valor do boleto deve ser positivo.")
    @Column(name = "TOTAL")
    private final BigDecimal total;

    /**
     * Construtor padrão, sem argumentos, requerido pela JPA
     */
    @SuppressWarnings("unused")
    private Bankslip() {
        this(null, null, null, null, null, null);
    }

    /**
     * Construtor padrão
     *
     * @param dueDate
     *         a data limite para o pagamento
     * @param customer
     *         o nome do cliente
     * @param total
     *         o valor do boleto
     */
    public Bankslip(final LocalDate dueDate, final String customer, final BigDecimal total) {
        this(null, UUID.randomUUID().toString(), dueDate, BankslipStatus.PENDING, customer, total);
    }

    /**
     * Calcula o valor da multa de acordo com quantidade de dias em atraso.
     *
     * @param referenceDate
     *         data de referencia para o cálculo de dias em atraso
     * @param calculators
     *         a lista de objetos {@link FineCalculator} responsáveis pelo cálculo da multa.
     * @return o valor da multa ou caso o boleto esteja em dia
     */
    public BigDecimal getFine(final List<FineCalculator> calculators, final LocalDate referenceDate) {
        return calculators.stream().filter(fineCalculator -> fineCalculator.shouldCalculate(this, referenceDate)).findFirst()
                .map(fineCalculator -> fineCalculator.calculate(this, referenceDate)).orElse(BigDecimal.ZERO);
    }

    /**
     * Calcula a quantidade de dias em atraso do boleto
     *
     * @param referenceDate
     *         data de referencia para o cálculo de dias em atraso * @return a quantidade de dias em atraso do boleto
     */
    public long getOverdueDays(final LocalDate referenceDate) {
        final long days = DAYS.between(dueDate, referenceDate);
        return days > 0 ? days : 0;
    }

    /**
     * Verifica se o boleto está expirado.
     *
     * @return <code>true</code> caso a data de pagamento do boleto seja anterior à data atual.
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(dueDate);
    }

    /**
     * Atualiza o status de um boleto.
     *
     * @param status
     *         o novo status do boleto
     */
    public void updateStatus(final BankslipStatus status) {
        if (isCanceled()) {
            throw new InvalidOperationException("Invalid operation. The bankslip has already been canceled.");
        }
        if (isPaid() && BankslipStatus.PENDING == status) {
            throw new InvalidOperationException("Invalid operation. The bankslip has already been paid.");
        }
        this.status = status;
    }

    private boolean isPaid() {
        return BankslipStatus.PAID == this.status;
    }

    private boolean isCanceled() {
        return BankslipStatus.CANCELED == this.status;
    }

}
