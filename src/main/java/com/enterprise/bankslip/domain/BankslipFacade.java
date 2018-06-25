/*
 * @(#)BankslipFacade.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A classe <code>BankslipFacade</code> expõe as funcionalidades relacionadas à {@link Bankslip}
 *
 * @Version 1.0.0 18/06/2018
 **/
@AllArgsConstructor
@Component
public class BankslipFacade {

    private final BankslipServices services;

    /**
     * Persiste um novo {@link Bankslip} na base de dados.
     *
     * @param bankslip
     *         o novo {@link Bankslip} a ser persistido na base.
     * @return o objeto {@link Bankslip} que foi persistido.
     */
    @Transactional
    public void createBankslip(final Bankslip bankslip) {
        services.createBankslip(bankslip);
    }

    /**
     * Atualiza o status de um objeto {@link Bankslip}
     *
     * @param code
     *         o código do boleto
     * @param status
     *         o novo status do boleto
     */
    @Transactional
    public void updateBankslip(final String code, final BankslipStatus status) {
        services.updateBankslip(code, status);
    }

    /**
     * Recupera todos os objetos {@link Bankslip} existentes na base de dados
     *
     * @return uma lista de objetos {@link Bankslip}
     */
    public List<Bankslip> getBankslip() {
        return services.getBankslip();
    }

    /**
     * Recupera um {@link Bankslip} da base de dados de acordo com o <code>code</code> passado como parâmetro
     *
     * @param code
     *         o código do {@link Bankslip}
     * @return um objeto {@link Bankslip}
     *
     * @throws BankslipNotFoundException
     *         caso nenhum objeto for encontrado na base de dados.
     */
    public Bankslip findByCode(final String code) {
        return services.findByCode(code);
    }

}
