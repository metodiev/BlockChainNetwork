package com.blockchain.node.data;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat ;


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
                if (validateTheFields == false) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("index");
                if (validateTheFields == false) {
                    break;
                }

                break;

            case 1:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("ArrayList");
                if (validateTheFields == false) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("transaction");
                if (validateTheFields == false) {
                    break;
                }
                break;

            case 2:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("int");
                if (validateTheFields == false) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("difficulty");
                if (validateTheFields == false) {
                    break;
                }
                break;

            case 3:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("prevBlockHash");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (validateTheFields == false)
                {
                    break;
                }
                break;

            case 4:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("minedBy");
                if (validateTheFields == false)
                {
                    break;
                }
                break;

            case 5:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("BlockDataHash");
                if (validateTheFields == false)
                {
                    break;
                }
                break;

            case 6:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("int");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("nonce");
                if (validateTheFields == false)
                {
                    break;
                }
                break;
            case 7:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("dateCreated");
                if (validateTheFields == false)
                {
                    break;
                }
                break;

            case 8:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("blockHash");
                if (validateTheFields == false)
                {
                    break;
                }
                break;

            case 9:
                validateTheFields = blockDataInfo.contains("private");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String[]");
                if (validateTheFields == false)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("pendingTransactions");
                if (validateTheFields == false)
                {
                    break;
                }
                break;
        }

        return validateTheFields;
    }


}

