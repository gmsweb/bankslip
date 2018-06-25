/*
 * @(#)AbstractIntegrationTest.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi;

import net.minidev.json.JSONValue;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Classe abstrata que contem métodos comuns utilizados nos testes de integração.
 *
 * @author Glauber Shibata
 * @Version 1.0.0 24/06/2018
 **/
public abstract class AbstractIntegrationTest {

    protected String getJsonAsString(final String filePath) throws UnsupportedEncodingException {
        return JSONValue.parse(new InputStreamReader(getClass().getResourceAsStream(filePath), "UTF-8")).toString();
    }
}
