package com.blockchain.node.core;


import com.blockchain.node.data.Transaction;
import com.google.gson.Gson;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.util.encoders.Hex;

import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SignatureException;
import java.util.ArrayList;
import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

/*
For each received transaction the Node does the following:
    Checks for missing / invalid fields / invalid field values
    Calculates the transaction data hash (unique transaction ID)
    Checks for collisions  duplicated transactions are skipped
    Validates the transaction public key, validates the signature
    Checks the sender account balance to be >= value + fee
    Checks whether value >= 0 and fee > 10 (min fee)
    Puts the transaction in the "pending transactions" pool
    Sends the transaction to all peer nodes through the REST API
    It goes from peer to peer until it reaches the entire network

*/
public class NodeReceiveVerifySend {
    static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
            curve.getN(), curve.getH());
    private Transaction transaction;
    private ArrayList<Transaction> receivedTxPool;
    private ArrayList<Transaction> pendingTxPool;

    /*    public String hardCodedJson =    {
                "fromAddress":"c3293572dbe6ebc60de4a20ed0e21446cae66b17",
                    "toAddress":"f51362b7351ef62253a227a77751ad9b2302f911",
                    "value":25000,
                    "fee":10,
                    "dateCreated":"2018-02-10T17:53:48.972Z",
                    "senderPubkey":"c74a8458cd7a7e48f4b7ae6f4ae9f56c5c88c0f03e7c59cb4132b9d9d1600bba1",
                    "transactionDataHash":"930bfd70a191d6f0e90cc0c34d379b70e715f009e40925130431c305aed384d7",
                    "senderSignature":"69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130",
                    "inedInBlockIndex":0,
                    "transerSuccessful":false,
                    "minedBlockIndex":0
            }*/

    public static void main(String[] args) throws UnsupportedEncodingException, SignatureException {
        NodeReceiveVerifySend nodeReceiveVerifySend = new NodeReceiveVerifySend();
        nodeReceiveVerifySend.hardCodedTransactionData();

    }


    private Transaction hardCodedTransactionData() throws UnsupportedEncodingException, SignatureException {
        Transaction transaction = new Transaction();
        transaction.setFromAddress("c3293572dbe6ebc60de4a20ed0e21446cae66b17");
        transaction.setToAddress("f51362b7351ef62253a227a77751ad9b2302f911");
        transaction.setValue(25000);
        transaction.setFee(10);
        transaction.setSenderPubkey("c74a8458cd7a7e48f4b7ae6f4ae9f56c5c88c0f03e7c59cb4132b9d9d1600bba1");
        transaction.setDateCreated("2018-02-10T17:53:48.972Z");
        transaction.setTransactionDataHash("930bfd70a191d6f0e90cc0c34d379b70e715f009e40925130431c305aed384d7");
        transaction.setSenderSignature("69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130");
        transaction.setrValue("69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6");
        transaction.setsValue("f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130");

        System.out.println("Is the Transaction Valid: "+verifyTransaction(transaction));

        return transaction;

    }
    private boolean checkBalance(){
        return true;
    }
    public boolean verifyTransaction(Transaction transaction) throws UnsupportedEncodingException, SignatureException {

        boolean isValidDataHash = verifyTxHash(transaction,transaction.getTransactionDataHash());
        boolean isValidPubKey =  verifyPubKey(transaction,transaction.getSenderPubkey());
        boolean isValidAddress = verifyAddress(transaction,transaction.getFromAddress());
        boolean isValidData = checkTransactionFieldAndData(transaction);
        boolean isGoodBalance = checkBalance();
        if (isValidData && isValidDataHash && isValidPubKey && isValidAddress && isGoodBalance) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verifyAddress(Transaction transaction, String fromAddress) throws SignatureException {

        Sign.SignatureData signature = new Sign.SignatureData((byte) 28, Numeric.hexStringToByteArray(transaction.getrValue()),
                Numeric.hexStringToByteArray(transaction.getsValue()));

        BigInteger pubKeyRecovered = Sign.signedMessageToKey(transaction.getTransactionDataHash().getBytes(), signature);
        String address = Keys.getAddress(pubKeyRecovered);
        boolean areEqualsAdresses = address.equals(fromAddress.toLowerCase());
        if (!areEqualsAdresses) {
            return true;
        } else {
            return false;
        }

    }

    private boolean verifyPubKey(Transaction transaction, String senderPubkey) throws SignatureException, UnsupportedEncodingException {

        Sign.SignatureData signature = new Sign.SignatureData((byte) 28, Numeric.hexStringToByteArray(transaction.getrValue()),
                Numeric.hexStringToByteArray(transaction.getsValue()));

        BigInteger pubKeyRecovered = Sign.signedMessageToKey(transaction.getTransactionDataHash().getBytes(), signature);

        if (!pubKeyRecovered.equals(senderPubkey)) {
            return true;
        } else {
            return false;
        }

    }

    // Checks for missing / invalid fields / invalid field values
    private static boolean checkTransactionFieldAndData(Transaction transaction) {
        boolean isVlaidFieldsInputTransacion = false;

        boolean switchValue = true;
        switch (1) {
            case 1:
                isVlaidFieldsInputTransacion = transaction.getFromAddress().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getToAddress().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = String.valueOf(transaction.getValue()).isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getDateCreated().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getSenderPubkey().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getTransactionDataHash().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getSenderSignature().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }
            case 2:
                isVlaidFieldsInputTransacion = transaction.getFromAddress().length() == 40;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getToAddress().length() == 40;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getValue() > 0;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getFee() >= 10;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getDateCreated().length() >= 6;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getSenderPubkey().length() == 66;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getTransactionDataHash().length() == 65;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getrValue().length() == 65;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getsValue().length() == 65;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }
        }

        return isVlaidFieldsInputTransacion;
    }

    private boolean verifyTxHash(Transaction transaction, String txDataHash) throws UnsupportedEncodingException {
        Transaction tran = new Transaction();
        tran.setToAddress(transaction.getToAddress());
        tran.setFromAddress(transaction.getFromAddress());
        tran.setSenderPubkey(transaction.getSenderPubkey());
        tran.setValue(transaction.getValue());
        tran.setFee(transaction.getFee());
        tran.setDateCreated(transaction.getDateCreated());
        Gson gson = new Gson();
        String tranJson = gson.toJson(tran);
        byte[] tranHash = calcSHA256(tranJson);
        if (BytesToHex(tranHash).equals(txDataHash)) {
            return true;
        } else {
            return false;
        }
    }

    private void transacationSendToNetwork(Transaction tx) {
        pendingTxPool.add(tx);
    }

    private static String BytesToHex(byte[] bytes) {
        return Hex.toHexString(bytes);
    }

    private static byte[] calcSHA256(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        SHA256Digest digest = new SHA256Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return result;

    }

}

