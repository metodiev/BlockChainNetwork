package com.blockchain.node.core;

import com.blockchain.node.data.Transaction;
import com.blockchain.node.data.Wallet;
import com.blockchain.node.staticdata.WalletStaticData;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WalletConnector {



    public void implementWallet(long fee, String from, String senderPubKey, String to, long value){
        Wallet wallet = new Wallet();

        ConvertDate getCurrentData = new ConvertDate();
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

        ConvertDate getCurrentData = new ConvertDate();
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


    public String getSplitTransactionHashdata() throws NoSuchAlgorithmException {

        Transaction transaction = new Transaction();


        ConvertDate getCurrentDate = new ConvertDate();
        String splitTransactionHashData = WalletStaticData.wallets.get(0).getFrom()  + WalletStaticData.wallets.get(0).getTo() +
                String.valueOf(WalletStaticData.wallets.get(0).getValue()) + String.valueOf(WalletStaticData.wallets.get(0).getFee()) +
                getCurrentDate  + WalletStaticData.wallets.get(0).getSenderPubKey();

        transaction.setSenderSignature(splitTransactionHashData);

        byte[] msgHash = Hash.sha3(splitTransactionHashData.getBytes());

        //Sign.SignatureData signature = Sign.signMessage(msgHash, keyPair, false);


        BigInteger privKey = new BigInteger("97ddae0f3a25b92268175400149d65d6887b9cefaf28ea2c078e05cdc15a3c0a", 16);
        BigInteger pubKey = Sign.publicKeyFromPrivate(privKey);
        ECKeyPair keyPair = new ECKeyPair(privKey, pubKey);


        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hashedString = messageDigest.digest(splitTransactionHashData.getBytes());

        String hashValueSHA384 = DatatypeConverter.printHexBinary(hashedString).toLowerCase();
        System.out.println(hashValueSHA384);
        return  splitTransactionHashData;
    }

}
