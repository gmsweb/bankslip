/*
 * @(#)Response.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe utilitária que encapsula uma mensagem de resposta a uma ação do usuário.
 *
 * @Version 1.0.0 23/06/2018
 **/
@AllArgsConstructor
@Getter
public class Response {

    /** A mensagem da resposta **/
    private final String message;
}
