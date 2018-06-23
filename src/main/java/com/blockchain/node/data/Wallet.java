package com.blockchain.node.data;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

public class Wallet {

    private String from;
    private String to;
    private long value;
    private long fee;
    private String dateCreated;
    private String senderPubKey;
    private String data;

    private String transactionDataHash;
    private BigInteger privKey = new BigInteger("97ddae0f3a25b92268175400149d65d6887b9cefaf28ea2c078e05cdc15a3c0a", 16);
    private BigInteger pubKey = Sign.publicKeyFromPrivate(privKey);
    private ECKeyPair keyPair = new ECKeyPair(privKey, pubKey);
    //TODO generate real  private key from Wallet


    public Wallet() {

    }

    public Wallet(String from, String to, long value, int fee, String dateCreated, String senderPubKey) {
        this.from = from;
        this.to = to;
        this.value = value;
        this.fee = fee;
        this.dateCreated = dateCreated;
        this.senderPubKey = senderPubKey;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getSenderPubKey() {
        return senderPubKey;
    }

    public void setSenderPubKey(String senderPubKey) {
        this.senderPubKey = senderPubKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
