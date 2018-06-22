package com.blockchain.node.data;

import java.util.ArrayList;

public class Block {

/*    Index: integer (unsigned)
    Transactions: Transaction[]
    Difficulty: integer (unsigned)
    PrevBlockHash: hex_number[64]
    MinedBy: address (40 hex digits)
    BlockDataHash: hex_number[64]
    Nonce: integer (unsigned)
    DateCreated: ISO8601_string
    BlockHash: hex_number[64]*/

    public int index;

    private ArrayList<Transaction> transaction;
    private int difficulty;
    private String prevBlockHash;
    private String minedBy;
    private String BlockDataHash;
    private int nonce;
    private String dateCreated;
    private  String blockHash;
    private String [] pendingTransactions;




public Block () {}

    public Block(int index, ArrayList<Transaction> transaction, int difficulty, String prevBlockHash, String minedBy, String blockDataHash, int nonce, String dateCreated, String blockHash, String[] pendingTransactions) {
        this.index = index;
        this.transaction = transaction;
        this.difficulty = difficulty;
        this.prevBlockHash = prevBlockHash;
        this.minedBy = minedBy;
        BlockDataHash = blockDataHash;
        this.nonce = nonce;
        this.dateCreated = dateCreated;
        this.blockHash = blockHash;
        this.pendingTransactions = pendingTransactions;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public String getMinedBy() {
        return minedBy;
    }

    public void setMinedBy(String minedBy) {
        this.minedBy = minedBy;
    }

    public String getBlockDataHash() {
        return BlockDataHash;
    }

    public void setBlockDataHash(String blockDataHash) {
        BlockDataHash = blockDataHash;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String[] getPendingTransactions() {
        return pendingTransactions;
    }

    public void setPendingTransactions(String[] pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
    }
}
