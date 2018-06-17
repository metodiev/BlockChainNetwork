package com.blockchain.node;

public class Transaction {

 /*   From: address (40 hex digits) To: address (40 hex digits)
    Value: integer (non-negative)
    Fee: integer (non-negative)
    DateCreated: ISO8601_string
    Data: string (optional)
    SenderPubKey: hex_number[65]
    TransactionDataHash: hex_number
    SenderSignature: hex_number[2][64]
    inedInBlockIndex: integer / null TransferSuccessful: bool*/


    private String address;
    private int value;
    private int fee;
    private String dateCreated;
    private String data;
    private String senderPubkey;
    private String transactionDataHash;
    private String  senderSignature;
    private int inedInBlockIndex;
    private boolean transerSuccessful;

    public Transaction(String address, int value, int fee, String dateCreated, String data, String senderPubkey, String transactionDataHash, String senderSignature, int inedInBlockIndex, boolean transerSuccessful) {
        this.address = address;
        this.value = value;
        this.fee = fee;
        this.dateCreated = dateCreated;
        this.data = data;
        this.senderPubkey = senderPubkey;
        this.transactionDataHash = transactionDataHash;
        this.senderSignature = senderSignature;
        this.inedInBlockIndex = inedInBlockIndex;
        this.transerSuccessful = transerSuccessful;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
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

    public int getInedInBlockIndex() {
        return inedInBlockIndex;
    }

    public void setInedInBlockIndex(int inedInBlockIndex) {
        this.inedInBlockIndex = inedInBlockIndex;
    }

    public boolean isTranserSuccessful() {
        return transerSuccessful;
    }

    public void setTranserSuccessful(boolean transerSuccessful) {
        this.transerSuccessful = transerSuccessful;
    }
}
