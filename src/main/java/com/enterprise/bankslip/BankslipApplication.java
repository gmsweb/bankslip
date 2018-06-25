/*
 * @(#)BankslipApplication.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip;

import com.enterprise.bankslip.config.SpringContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringContextConfiguration.class)
public class BankslipApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankslipApplication.class, args);
    }

}

