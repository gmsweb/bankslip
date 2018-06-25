/*
 * @(#)BankslipRepository.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * A classe <code>BankslipRepository</code> lida com operações de persistência de objetos {@link Bankslip}
 *
 * @Version 1.0.0 18/06/2018
 **/
@Repository
public interface BankslipRepository extends JpaRepository<Bankslip, Long> {

    /**
     * @param code
     *         o codigo do boleto
     * @return
     */
    Optional<Bankslip> findByCode(final String code);
}
