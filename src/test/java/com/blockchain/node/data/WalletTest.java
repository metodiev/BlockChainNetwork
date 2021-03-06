package com.blockchain.node.data;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class WalletTest {
    @Test
    public void validateFieldsofBlock() throws ClassNotFoundException, NoSuchFieldException {
        Wallet node = new Wallet();
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

        switch (blockFieldId) {
            case 0:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if(!validateTheFields)
                {
                    break;
                }
                validateTheFields = blockDataInfo.contains("from");
                if (!validateTheFields) {
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
                validateTheFields = blockDataInfo.contains("to");
                if (!validateTheFields) {
                    break;
                }
                break ;

            case 2:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("long");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("value");
                if (!validateTheFields ) {
                    break;
                }
                break ;
            case 3:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("long");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("fee");
                if (!validateTheFields ) {
                    break;
                }
                break;
            case 4:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("dateCreated");
                if (!validateTheFields ) {
                    break;
                }
                break;
            case 5:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("senderPubKey");
                if (!validateTheFields ) {
                    break;
                }
                break;

            case 6:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("data");
                if (!validateTheFields ) {
                    break;
                }
                break;
            case 7:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("String");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("transactionDataHash");
                if (!validateTheFields ) {
                    break;
                }
                break ;
            case 8:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("BigInteger");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("privKey");
                if (!validateTheFields ) {
                    break;
                }
                break ;
            case 9:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("BigInteger");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("pubKey");
                if (!validateTheFields ) {
                    break;
                }
                break ;
            case 10:
                validateTheFields = blockDataInfo.contains("private");
                if (!validateTheFields) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("ECKeyPair");
                if (!validateTheFields ) {
                    break;
                }
                validateTheFields = blockDataInfo.contains("keyPair");
                if (!validateTheFields ) {
                    break;
                }
                break ;





        }
        return validateTheFields;
    }
}
