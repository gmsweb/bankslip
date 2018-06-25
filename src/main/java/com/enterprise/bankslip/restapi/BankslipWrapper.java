/*
 * @(#)BankslipWrapper.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi;

import com.enterprise.bankslip.domain.Bankslip;
import com.enterprise.bankslip.restapi.serializers.BankslipWrapperDeserializer;
import com.enterprise.bankslip.restapi.serializers.BankslipWrapperSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <Class coments go here>
 *
 * @Version 1.0.0 19/06/2018
 **/
@AllArgsConstructor
@Getter
@JsonSerialize(using = BankslipWrapperSerializer.class)
@JsonDeserialize(using = BankslipWrapperDeserializer.class)
public class BankslipWrapper {

    private Bankslip bankslip;

}
