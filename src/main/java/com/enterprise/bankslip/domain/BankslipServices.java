/*
 * @(#)BankslipServices.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Classe de serviço que disponibiliza métodos que lidam com a persistência de objetos {@link Bankslip}
 *
 * @Version 1.0.0 18/06/2018
 **/
@AllArgsConstructor
@Service
public class BankslipServices {

    private final BankslipRepository repository;

    /**
     * Persiste um novo objeto {@link Bankslip} na base de dados
     *
     * @param bankslip
     *         o novo boleto a ser persistido
     */
    public void createBankslip(final Bankslip bankslip) {
        repository.save(bankslip);
    }

    /**
     * Recupera todos os boletos constantes na base de dados
     *
     * @return uma lista de objetos {@link Bankslip}
     */
    public List<Bankslip> getBankslip() {
        return repository.findAll();
    }

    /**
     * Recupera um boleto de acordo com o código passado como parâmetro
     *
     * @param code
     *         o código do boleto
     * @return um objeto {@link Bankslip}
     *
     * @throws BankslipNotFoundException
     *         caso nenhum boleto for encontrado com o código apresentado
     */
    public Bankslip findByCode(final String code) {
        validateCode(code);
        final Optional<Bankslip> opt = repository.findByCode(code);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new BankslipNotFoundException(code);
    }

    /**
     * Atualiza o status de um boleto
     *
     * @param code
     *         o código do boleto a ser atualizado
     * @param status
     *         o novo status do boleto
     */
    public void updateBankslip(final String code, final BankslipStatus status) {
        validateCode(code);
        final Bankslip bankslip = findByCode(code);
        bankslip.updateStatus(status);
        repository.save(bankslip);
    }

    private void validateCode(final String code) {
        try {
            UUID.fromString(code);
        } catch (final IllegalArgumentException exception) {
            throw new InvalidBankslipCodeException(code);
        }
    }
}
