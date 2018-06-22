package com.blockchain.node.data;

import org.junit.Test;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class BlockTest {

    @Test
    public void validateFieldsofBlock() throws ClassNotFoundException, NoSuchFieldException {
        Block block = new Block();
        Class cls = block.getClass();

        // returns the array of Field objects
        Field[] fields = cls.getDeclaredFields();
        boolean isValidClass = true;
        for (int i = 0; i < fields.length; i++) {

            System.out.println("Field = " + fields[i].toString());
            isValidClass = checkBlockData(i, fields[i].toString());
            if (isValidClass == false){
                break;
            }
        }
        assertFalse(String.valueOf(isValidClass), true);

    }

    private boolean checkBlockData(int blockFieldId, String blockDataInfo) {
        boolean validateTheFields = true;

        switch (blockFieldId) {
            case 1:
                validateTheFields = isContain(blockDataInfo, "private");
                validateTheFields = isContain(blockDataInfo, "index");
                break;
        }

        return validateTheFields;
    }

    private boolean isContain(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        System.out.println(m.find());
        return m.find();
    }
}

