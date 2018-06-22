package com.blockchain.node.data;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeDataTest {
    @Test
    public void validateFieldsofBlock() throws ClassNotFoundException, NoSuchFieldException {
        NodeData node= new NodeData();
        Class cls = node.getClass();

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
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("nodeId");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if(!validateTheFields)
                {
                    break;
                }
                break ;
            case 1:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("selfURL");
                if (!validateTheFields) {
                    break;
                }
                break ;

            case 2:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("peers");
                if (!validateTheFields ) {
                    break;
                }
                break ;
            case 3:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("LinkedList");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("chain");
                if (!validateTheFields ) {
                    break;
                }
                break;

        }

        return validateTheFields;
    }

}
