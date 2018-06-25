--liquibase formatted sql

--changeset shibata:01
--comment: Create table BANKSLIP
CREATE TABLE BANKSLIP (
    ID  SERIAL PRIMARY KEY,
    CODE VARCHAR (50) NOT NULL,
    DUE_DATE DATE NOT NULL,
    STATUS INT NOT NULL,
    CUSTOMER VARCHAR(300) NOT NULL,
    TOTAL NUMERIC NOT NULL
);
--rollback drop table BANKSLIP;
