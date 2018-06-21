package com.blockchain.node.data;

public class Transaction {

 /*   From: address (40 hex digits)
  To: address (40 hex digits)
    Value: integer (non-negative)
    Fee: integer (non-negative)
    DateCreated: ISO8601_string
    Data: string (optional)
    SenderPubKey: hex_number[65]
    TransactionDataHash: hex_number
    SenderSignature: hex_number[2][64]
    inedInBlockIndex: integer / null TransferSuccessful: bool*/


    private String fromAddress;
    private String toAddress;
    private long value;
    private long fee;
    private String dateCreated;
    private String data;
    private String senderPubkey;
    private String transactionDataHash;
    private String senderSignature;
    private long inedInBlockIndex;
    private boolean transerSuccessful;
    private long minedBlockIndex;
    private String rValue;
    private String sValue;

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSenderPubkey() {
        return senderPubkey;
    }

    public void setSenderPubkey(String senderPubkey) {
        this.senderPubkey = senderPubkey;
    }

    public String getTransactionDataHash() {
        return transactionDataHash;
    }

    public void setTransactionDataHash(String transactionDataHash) {
        this.transactionDataHash = transactionDataHash;
    }

    public String getSenderSignature() {
        return senderSignature;
    }

    public void setSenderSignature(String senderSignature) {
        this.senderSignature = senderSignature;
    }

    public long getInedInBlockIndex() {
        return inedInBlockIndex;
    }

    public void setInedInBlockIndex(long inedInBlockIndex) {
        this.inedInBlockIndex = inedInBlockIndex;
    }

    public boolean isTranserSuccessful() {
        return transerSuccessful;
    }

    public void setTranserSuccessful(boolean transerSuccessful) {
        this.transerSuccessful = transerSuccessful;
    }

    public long getMinedBlockIndex() {
        return minedBlockIndex;
    }

    public void setMinedBlockIndex(long minedBlockIndex) {
        this.minedBlockIndex = minedBlockIndex;
    }

    public String getrValue() {
        return rValue;
    }

    public void setrValue(String rValue) {
        this.rValue = rValue;
    }

    public String getsValue() {
        return sValue;
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    public Transaction() {
    }

    public Transaction(String fromAddress, String toAddress, long value, long fee, String dateCreated, String data, String senderPubkey, String transactionDataHash, String senderSignature, long inedInBlockIndex, boolean transerSuccessful, long minedBlockIndex, String rValue, String sValue) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.value = value;
        this.fee = fee;
        this.dateCreated = dateCreated;
        this.data = data;
        this.senderPubkey = senderPubkey;
        this.transactionDataHash = transactionDataHash;
        this.senderSignature = senderSignature;
        this.inedInBlockIndex = inedInBlockIndex;
        this.transerSuccessful = transerSuccessful;
        this.minedBlockIndex = minedBlockIndex;
        this.rValue = rValue;
        this.sValue = sValue;
    }

}

