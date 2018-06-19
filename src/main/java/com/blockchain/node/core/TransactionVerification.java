package com.blockchain.node.core;

import com.blockchain.node.Transaction;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.Signature;
import java.security.SignatureException;

public class TransactionVerification {
    public static  void main (String [] args) throws SignatureException {
        String testJson = "[{\"index\":0,\"transactions\":[{\"from\":\"0000000000000000000000000000000000000000\",\"to\":\"f3a1e69b6176052fcc4a3248f1c5a91dea308ca9\",\"value\":1000000000000,\"fee\":0,\"dateCreated\":\"2018-01-01T00:00:00.000Z\",\"data\":\"genesis tx\",\"senderPubKey\":\"00000000000000000000000000000000000000000000000000000000000000000\",\"transactionDataHash\":\"8a684cb8491ee419e7d46a0fd2438cad82d1278c340b5d01974e7beb6b72ecc2\",\"senderSignature\":[\"0000000000000000000000000000000000000000000000000000000000000000\",\"0000000000000000000000000000000000000000000000000000000000000000\"],\"minedInBlockIndex\":0,\"transferSuccessful\":true}],\"difficulty\":0,\"minedBy\":\"0000000000000000000000000000000000000000\",\"blockDataHash\":\"15cc5052fb3c307dd2bfc6bcaa057632250ee05104e4fb7cc75e59db1a92cefc\",\"nonce\":0,\"dateCreated\":\"2018-01-01T00:00:00.000Z\",\"blockHash\":\"c6da93eb4249cb5ff4f9da36e2a7f8d0d61999221ed6910180948153e71cc47f\"}]";
        String[] strInput = testJson.split("\",");

        String [] p = strInput[0].split(",");
        String [] q = p[1].split(":");

        for (int i = 0; i <q.length ; i++) {
            q[i] = q[i].replace("\"","").replace("[{","");
            System.out.println(q[2]);
        }
           // String Signature = "0x00000000000000000000000000000000000000000000000000000000000000000";
        String r = "0000000000000000000000000000000000000000000000000000000000000000";
        String s = "0000000000000000000000000000000000000000000000000000000000000000";
       // String message = "0000000000000000000000000000000000000000000000000000000000000000";


          String  message = "genesis tx";
        // String signature = "0000000000000000000000000000000000000000000000000000000000000000";

        Sign.SignatureData signature = new Sign.SignatureData((byte) 28, Numeric.hexStringToByteArray(r),
              Numeric.hexStringToByteArray(s));
        String from = "f3a1e69b6176052fcc4a3248f1c5a91dea308ca9";
        BigInteger publicKeyRecovered = Sign.signedMessageToKey(message.getBytes(), signature);
        System.out.println(publicKeyRecovered);
        String address = Keys.getAddress(publicKeyRecovered);


        address = new String( address);
        System.out.println("THIS IS THE ADDRESS 0x" + address);
        System.out.println("THIS IS THE FROM:" + from);
        boolean areEqualAdresses = address.equals(from);
        if (areEqualAdresses) {
            System.out.println("valid");
        } else {
            System.out.println("invalid");
        }


    }

        public boolean isValidTransaction (Transaction transaction) throws SignatureException {
            boolean isValid = false;
            String testJson = "[{\"index\":0,\"transactions\":[{\"from\":\"0000000000000000000000000000000000000000\",\"to\":\"f3a1e69b6176052fcc4a3248f1c5a91dea308ca9\",\"value\":1000000000000,\"fee\":0,\"dateCreated\":\"2018-01-01T00:00:00.000Z\",\"data\":\"genesis tx\",\"senderPubKey\":\"00000000000000000000000000000000000000000000000000000000000000000\",\"transactionDataHash\":\"8a684cb8491ee419e7d46a0fd2438cad82d1278c340b5d01974e7beb6b72ecc2\",\"senderSignature\":[\"0000000000000000000000000000000000000000000000000000000000000000\",\"0000000000000000000000000000000000000000000000000000000000000000\"],\"minedInBlockIndex\":0,\"transferSuccessful\":true}],\"difficulty\":0,\"minedBy\":\"0000000000000000000000000000000000000000\",\"blockDataHash\":\"15cc5052fb3c307dd2bfc6bcaa057632250ee05104e4fb7cc75e59db1a92cefc\",\"nonce\":0,\"dateCreated\":\"2018-01-01T00:00:00.000Z\",\"blockHash\":\"c6da93eb4249cb5ff4f9da36e2a7f8d0d61999221ed6910180948153e71cc47f\"}]";
            String[] strInput = testJson.split("\",");

            String [] p = strInput[0].split(",");
            String [] q = p[1].split(":");

            for (int i = 0; i <q.length ; i++) {
                q[i] = q[i].replace("\"","").replace("[{","");
                System.out.println(q[i]);
            }
            String Signature = "0x0000000000000000000000000000000000000000000000000000000000000000";


            String r = Signature.substring(0, 66);
            String s = "0x" + Signature.substring(66, 130);
            String message = "0000000000000000000000000000000000000000000000000000000000000000";


         //   String  message = "0000000000000000000000000000000000000000000000000000000000000000";
           // String signature = "0000000000000000000000000000000000000000000000000000000000000000";

            Sign.SignatureData signature = new Sign.SignatureData((byte) 28, Numeric.hexStringToByteArray(r),
                    Numeric.hexStringToByteArray(s));

            BigInteger publicKeyRecovered = Sign.signedMessageToKey(message.getBytes(), signature);
            String address = Keys.getAddress(publicKeyRecovered);


            address = new String("0x" + address);
            System.out.println("0x" + address);

            boolean areEqualAdresses = address.equals(address.toLowerCase());
            if (areEqualAdresses) {
                System.out.println("valid");
            } else {
                System.out.println("invalid");
            }
        return false ;
        }







        public boolean isValidTransactionInput(Transaction transaction)
        {
            boolean isValid = true;
            if(!Character.isDigit(transaction.getInedInBlockIndex()))
            {

                isValid = false;
            }
            if(!Character.isDigit(transaction.getMinedBlockIndex()))
            {

                isValid = false;
            }
            if(!Character.isDigit(transaction.getFee()))
            {

                isValid = false;
            }
            if(!Character.isDigit(transaction.getValue()))
            {

                isValid = false;
            }

            return isValid;
        }
    }
