/*
 * @(#)BankslipControllerTest.java 1.0 25/06/2018
 *
 * Copyright (c) 2018, The Company. All rights reserved.
 * The Company S/A proprietary/confidential. Use is subject to license terms.
 */

package com.enterprise.bankslip.restapi;

import com.enterprise.bankslip.domain.Bankslip;
import com.enterprise.bankslip.domain.BankslipRepository;
import com.enterprise.bankslip.domain.BankslipStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Suite de testes para a classe <code>BankslipController</code>
 *
 * @Version 1.0.0 24/06/2018
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BankslipControllerTest extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BankslipRepository repository;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void createBankslipTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/bankslip.json"))).andExpect(status().isCreated()).andReturn();
        assertEquals("{\"message\":\"Bankslip created.\"}", mvcResult.getResponse().getContentAsString());
        final List<Bankslip> bankslips = repository.findAll();
        assertEquals(3, bankslips.size());
    }

    @Test
    public void createBankslipWithEmptyBodyTest() throws Exception {
        final MvcResult mvcResult = mockMvc
                .perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8).content("{}"))
                .andExpect(status().isBadRequest()).andReturn();
        assertEquals("{\"message\":\"Bankslip not provided in the request body\"}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createBankslipWithInvalidDateFormatTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/bankslip_with_invalid_date_format.json")))
                .andExpect(status().isUnprocessableEntity()).andReturn();
        assertEquals("{\"message\":\"Invalid bankslip provided. The date field MUST be provided as yyyy-MM-dd\"}",
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void createBankslipWithInvalidCustomerNameTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/bankslip_with_invalid_customer_name.json")))
                .andExpect(status().isUnprocessableEntity()).andReturn();
        JSONAssert.assertEquals(getJsonAsString("/json/assertions/invalid_bankslip_message.json"),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void createBankslipWithNoCustomerNameTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/bankslip_with_no_customer_name.json")))
                .andExpect(status().isUnprocessableEntity()).andReturn();
        JSONAssert.assertEquals(getJsonAsString("/json/assertions/invalid_bankslip_message.json"),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void createBankslipWithInvalidValueTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/bankslip_with_invalid_value.json"))).andExpect(status().isUnprocessableEntity())
                .andReturn();
        JSONAssert.assertEquals(getJsonAsString("/json/assertions/invalid_bankslip_message.json"),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void createBankslipWithNegativeValueTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/bankslip_with_negative_value.json"))).andExpect(status().isUnprocessableEntity())
                .andReturn();
        JSONAssert.assertEquals(getJsonAsString("/json/assertions/invalid_bankslip_message.json"),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void createBankslipWithZeroValueTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(post("/rest/bankslips").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/bankslip_with_zero_value.json"))).andExpect(status().isUnprocessableEntity())
                .andReturn();
        JSONAssert.assertEquals(getJsonAsString("/json/assertions/invalid_bankslip_message.json"),
                mvcResult.getResponse().getContentAsString(), true);
    }

    @Test
    public void getBankslipTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/rest/bankslips/84e8adbf-1a14-403b-ad73-d78ae19b59bf"))
                .andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals(getJsonAsString("/json/assertions/bankslip.json"), mvcResult.getResponse().getContentAsString(),
                false);
    }

    @Test
    public void getBankslipTestThrowingBankslipNotFoundException() throws Exception {
        mockMvc.perform(get("/rest/bankslips/84e8adbf-1a14-403b-ad73-d78ae19b59cc")).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void getBankslipTestWithInvalidCode() throws Exception {
        mockMvc.perform(get("/rest/bankslips/84e8adbf-1a14-403b-ad73-d78ae19b59zz")).andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void listBankslipTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/rest/bankslips")).andExpect(status().isOk()).andReturn();
        JSONAssert.assertEquals(getJsonAsString("/json/assertions/available_bankslips.json"),
                mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    public void updateBankslipTest() throws Exception {
        mockMvc.perform(put("/rest/bankslips/84e8adbf-1a14-403b-ad73-d78ae19b59bf").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(getJsonAsString("/json/canceled_status.json"))).andExpect(status().isOk()).andReturn();
        final Optional<Bankslip> updatedBankslip = repository.findByCode("84e8adbf-1a14-403b-ad73-d78ae19b59bf");
        assertTrue(updatedBankslip.isPresent());
        Assert.assertEquals(BankslipStatus.CANCELED, updatedBankslip.get().getStatus());
    }

    @Test
    public void updateBankslipWithInvalidStatusTest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(
                put("/rest/bankslips/84e8adbf-1a14-403b-ad73-d78ae19b59bf").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(getJsonAsString("/json/invalid_status.json"))).andExpect(status().isBadRequest()).andReturn();
    }

}
