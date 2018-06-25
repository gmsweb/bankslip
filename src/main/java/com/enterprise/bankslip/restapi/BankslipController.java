/*
 * @(#)BankslipController.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi;

import com.enterprise.bankslip.domain.Bankslip;
import com.enterprise.bankslip.domain.BankslipFacade;
import com.enterprise.bankslip.restapi.serializers.ListWrapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <Class coments go here>
 *
 * @Version 1.0.0 18/06/2018
 **/
@AllArgsConstructor
@RestController
@RequestMapping("/rest/bankslips")
public class BankslipController {

    private final BankslipFacade facade;

    /**
     * Cria um novo boleto que deverá ser persistido na base de dados
     *
     * @param bankslipWrapper
     *         o objeto que contem o boleto a ser persistido
     * @return {@link HttpStatus#CREATED} quando um boleto é criado com sucesso.
     */
    @PostMapping
    public ResponseEntity createBankslip(@RequestBody final BankslipWrapper bankslipWrapper) {
        facade.createBankslip(bankslipWrapper.getBankslip());
        return new ResponseEntity(new Response("Bankslip created."), HttpStatus.CREATED);
    }

    /**
     * Exibe os detalhes do boleto correspondente ao código passado como parâmetro
     *
     * @param code
     *         o código do boleto
     * @return
     */
    @GetMapping("/{code}")
    public ResponseEntity<BankslipWrapper> getBankslip(@PathVariable final String code) {
        return new ResponseEntity(new BankslipWrapper(facade.findByCode(code)), HttpStatus.OK);
    }

    /**
     * Lista todos os boletos existentes na base de dados.
     *
     * @return a lista de boletos constantes na base de dados.
     */
    @GetMapping
    public ListWrapper<Bankslip> listBankslip() {
        return new ListWrapper<>(facade.getBankslip());
    }

    /**
     * Atualiza um boleto
     *
     * @param code
     *         o código do boleto
     * @param bankslipStatusWrapper
     *         o novo status do boleto
     */
    @PutMapping("/{code}")
    public ResponseEntity<Response> updateBankslip(@PathVariable final String code,
            @RequestBody final BankslipStatusWrapper bankslipStatusWrapper) {
        facade.updateBankslip(code, bankslipStatusWrapper.getStatus());
        return new ResponseEntity(new Response(bankslipStatusWrapper.getStatusMessage()), HttpStatus.OK);
    }

}
