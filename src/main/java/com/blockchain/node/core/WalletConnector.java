package com.blockchain.node.core;

import com.blockchain.node.data.Transaction;
import com.blockchain.node.data.Wallet;
import com.blockchain.node.staticdata.WalletStaticData;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECKey;

public class WalletConnector {

    private BigInteger privateKey;
    private BigInteger publicKey;
    private String hashValueSHA256;

    public void implementWallet(long fee, String from, String senderPubKey, String to, long value){
        Wallet wallet = new Wallet();

        GetCurrentDate getCurrentData = new GetCurrentDate();
        wallet.setDateCreated(getCurrentData.getCurrentdate());
        wallet.setFee(fee);
        wallet.setFrom(from);
        wallet.setSenderPubKey(senderPubKey);
        wallet.setTo(to);
        wallet.setValue(value);

        System.out.println(wallet.getFee());
        //call the static data to add a new wallet
        WalletStaticData.addNewWallet(wallet);
    }

    public void implementWallet(long fee, String from, String senderPubKey, String to, long value, String data){
        Wallet wallet = new Wallet();

        GetCurrentDate getCurrentData = new GetCurrentDate();
        wallet.setDateCreated(getCurrentData.getCurrentdate());
        wallet.setFee(fee);
        wallet.setFrom(from);
        wallet.setSenderPubKey(senderPubKey);
        wallet.setTo(to);
        wallet.setValue(value);
        wallet.setData(data);

        System.out.println(wallet.getFee());
        //call the static data to add a new wallet
        WalletStaticData.addNewWallet(wallet);
    }


    public String [] generateSignTransaction() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Transaction transaction = new Transaction();

        GetCurrentDate getCurrentDate = new GetCurrentDate();
        String splitTransactionHashData = WalletStaticData.wallets.get(0).getFrom()  + WalletStaticData.wallets.get(0).getTo() +
                String.valueOf(WalletStaticData.wallets.get(0).getValue()) + String.valueOf(WalletStaticData.wallets.get(0).getFee()) +
                getCurrentDate  + WalletStaticData.wallets.get(0).getSenderPubKey();

        transaction.setSenderSignature(splitTransactionHashData);
        RandomKeyPairGenerator radomGenerator = new RandomKeyPairGenerator();
        privateKey = radomGenerator.getRadomPrivateKey();
        publicKey = radomGenerator.getPublicKeyFromPrivateKey(privateKey);

       //  String privateKey =  "c1fa7dc69c28a37c44f0761376ab502ebbf7206012d2c8e4b5b0fe3b085682a3";






        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedString = messageDigest.digest(splitTransactionHashData.getBytes());

        this.hashValueSHA256 = DatatypeConverter.printHexBinary(hashedString).toLowerCase();
        System.out.println(hashValueSHA256);

        ECKeyPair keyPair = new ECKeyPair(privateKey,  publicKey);
        byte[] msgHash = Hash.sha3(splitTransactionHashData.getBytes());
        System.out.println(hashValueSHA256);
        Sign.SignatureData signature = Sign.signMessage(msgHash, keyPair, false);

        String [] arrOfSignAndHash = new String[2];
            arrOfSignAndHash[0]  =  signature.getV() +
                    Hex.toHexString(signature.getR()) +
                    Hex.toHexString(signature.getS());

           arrOfSignAndHash[1] = hashValueSHA256;

        return  arrOfSignAndHash;
    }

    public  static void main(String[] args ) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        WalletStaticData staticData = new WalletStaticData();
        Wallet wallet = new Wallet();
        wallet.setFrom("44fe0696beb6e24541cc0e8728276c9ec3af2675");
        wallet.setTo("9a9f082f37270ff54c5ca4204a0e4da6951fe917");
        wallet.setValue(25000);
        wallet.setFee(1000000);
        GetCurrentDate getCurrentDate = new GetCurrentDate();
        wallet.setDateCreated(getCurrentDate.getCurrentdate());
        wallet.setSenderPubKey("2a1d79fb8743d0a4a8501e0028079bcf82a4");

        staticData.addNewWallet(wallet);

        WalletConnector walletConnector = new WalletConnector();

        walletConnector.generateSignTransaction();
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public String getHashValueSHA256() {
        return hashValueSHA256;
    }

    public void setHashValueSHA256(String hashValueSHA256) {
        this.hashValueSHA256 = hashValueSHA256;
    }
}
