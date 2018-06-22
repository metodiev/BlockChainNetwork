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

    @Test
    public void debugController() throws Exception {
        assertThat(debugController).isNotNull();
    }

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
            isValidClass = checkBlockData(i, fields[i].toString());
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


        validateTheFields = blockDataInfo.contains("from");






        return validateTheFields;
        }



    }


