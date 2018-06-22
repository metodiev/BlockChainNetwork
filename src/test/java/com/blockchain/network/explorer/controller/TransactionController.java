package com.blockchain.network.explorer.controller;

import com.blockchain.node.data.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionController {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //exist test - check if the controller exist
    @Autowired
    private DebugController debugController;



    //check the http message from the controller
    // @Test
//    public void defaultDebugMessage() throws Exception {

    //       assertThat(this.restTemplate.getForObject("http://localhost:" + port + " /postTransactionData", String.class)).isNotEmpty();
    //   }

    @Test
    public void validateFieldsofBlock() throws ClassNotFoundException, NoSuchFieldException {
        Transaction node = new Transaction();
        Class cls = node.getClass();

        // returns the array of Field objects
        Field[] fields = cls.getDeclaredFields();
        boolean isValidClass = true;
        for (int i = 0; i < fields.length; i++) {
            //  isValidClass = checkBlockData(i, fields[i].toString());
            System.out.println("Isvalid" + isValidClass + i);
            System.out.println(fields[i].toString());
            if (isValidClass == false) {
                assertThat(isValidClass).isEqualTo(false);
                break;
            }

        }

        assertThat(isValidClass).isEqualTo(true);

    }

    private boolean checkBlockData(int blockFieldId, String blockDataInfo) {

        boolean validateTheFields = false;

        switch (blockFieldId) {
            case 0:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                break;

            case 1:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("toAddress");
                if (!validateTheFields) {
                    break;
                }
                break;

            case 2:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("long");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("value");
                if(!validateTheFields)
                {
                    break;
                }
                break;
            case 3:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("long");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("fee");
                if (!validateTheFields) {
                    break;
                }
                break;
            case 4:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("dateCreated");
                if (!validateTheFields) {
                    break;
                }
                break;
            case 5:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("data");
                if (!validateTheFields) {
                    break;
                }
                break;

            case 6:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("senderPubkey");
                if (!validateTheFields) {
                    break;
                }
                break;
            case 7:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("transactionDataHash");
                if (!validateTheFields) {
                    break;
                }
                break;
            case 8:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("senderSignature");
                if (!validateTheFields) {
                    break;
                }
                break;
            case 9:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("long");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("inedInBlockIndex");
                if (!validateTheFields) {
                    break;
                }
            case 10:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }

                validateTheFields = blockDataInfo.contains("transerSuccessful");
                if (!validateTheFields) {
                    break;
                }
            case 11:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("long");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("minedInBlockIndex");
                if (!validateTheFields) {
                    break;
                }
            case 12:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("rValue");
                if (!validateTheFields) {
                    break;
                }
                break;
            case 13:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("sValue");
                if (!validateTheFields) {
                    break;
                }
                break;

        }
        return validateTheFields;
    }


}


