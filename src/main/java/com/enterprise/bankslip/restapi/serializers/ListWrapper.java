/*
 * @(#)ListWrapper.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi.serializers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * A classe {@link ListWrapper} e um value object, responsável por encapsular uma lista genérica, cujos elementos serao
 * serializados em formato JSON
 *
 * @Version 1.0.0 22/06/2018
 **/
@AllArgsConstructor
@Getter
@JsonSerialize(using = ListWrapperSerializer.class)
public class ListWrapper<T> {

    private List<T> objects;

    @SuppressWarnings("unused")
    private ListWrapper() {
        this.objects = Collections.emptyList();
    }
}
