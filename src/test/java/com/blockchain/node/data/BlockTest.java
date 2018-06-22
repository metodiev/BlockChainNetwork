package com.blockchain.node.data;

import org.junit.Test;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;


public class BlockTest {

    @Test
    public void validateFieldsofBlock() throws ClassNotFoundException, NoSuchFieldException {
        Block block = new Block();
        Class cls = block.getClass();

        // returns the array of Field objects
        Field[] fields = cls.getDeclaredFields();
        boolean isValidClass = true;
        for (int i = 0; i < fields.length; i++) {
            isValidClass = checkBlockData(i, fields[i].toString());
            System.out.println("Isvalid" + isValidClass + i);
            System.out.println(fields[i].toString());
            if (isValidClass == false  ){
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
                validateTheFields = blockDataInfo.contains("index");

                break;

            case 1:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("ArrayList");
                validateTheFields = blockDataInfo.contains("transaction");
                break;

            case 2:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("difficulty");
                break;

            case 3:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("prevBlockHash");
                break;

            case 4:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("minedBy");
                break;

            case 5:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("BlockDataHash");
                break;

            case 6:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("nonce");
                break;
            case 7:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("dateCreated");
                break;

            case 8:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("blockHash");
                break;

            case 9:
                validateTheFields = blockDataInfo.contains("private");
                validateTheFields = blockDataInfo.contains("pendingTransactions");
                break;
        }

        return validateTheFields;
    }


}

