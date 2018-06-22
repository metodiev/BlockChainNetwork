package com.blockchain.node.data;

public class Blockchain {

   /* Blocks: Block[]
    PendingTransactions: Transaction[]
    CurrentDifficulty: integer
    MiningJobs: map(blockDataHash → Block)*/

    private Block[] blocks;
    private Transaction[] pendingTransactions;
    private int currentDifficulty;
    //TODO finish miningJobs data field


    public Blockchain(Block[] blocks, Transaction[] pendingTransactions, int currentDifficulty) {
        this.blocks = blocks;
        this.pendingTransactions = pendingTransactions;
        this.currentDifficulty = currentDifficulty;
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[] blocks) {
        this.blocks = blocks;
    }

    public Transaction[] getPendingTransactions() {
        return pendingTransactions;
    }

    public void setPendingTransactions(Transaction[] pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
    }

    public int getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(int currentDifficulty) {
        this.currentDifficulty = currentDifficulty;
    }
}