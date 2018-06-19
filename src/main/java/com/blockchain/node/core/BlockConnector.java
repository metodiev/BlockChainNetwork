package com.blockchain.node.core;

import com.blockchain.node.data.Block;
import com.blockchain.node.data.Transaction;

import java.text.DateFormat;
import java.util.ArrayList;

public class BlockConnector {

    /***
     *
     * @return
     * Block
     */
    public Block implementGenesisBlock() {

        Block genesisBlock = new Block();
        genesisBlock.setIndex(0);
        //genesisBlock.setTransaction();
        genesisBlock.setDifficulty(0);
        genesisBlock.setMinedBy("0000000000000000000000000000000000000000");
        //TO DO genesisBlock.setBlockHash("");
        genesisBlock.setNonce(0);
        genesisBlock.setDateCreated(DateFormat.getDateInstance().toString());
        //TO DOgenesisBlock.setBlockHash("");

        return genesisBlock;
    }

    /***
     *
     * @param blockHash
     * @param dateCreated
     * @param nonce
     * @param minedBy
     * @param difficulty
     * @param transactions
     * @param pendingTransactions
     * @param previousBlockHash
     * @param index
     * @return
     */
    public Block implementNewBlock(String blockHash, String dateCreated, int nonce, String minedBy, int difficulty, ArrayList<Transaction> transactions, String[] pendingTransactions, String previousBlockHash, int index) {
        Block newBlock = new Block();
        newBlock.setBlockHash(blockHash);
        newBlock.setDateCreated(dateCreated);
        newBlock.setNonce(nonce);
        newBlock.setMinedBy(minedBy);
        newBlock.setDifficulty(difficulty);
        newBlock.setTransaction(transactions);
        newBlock.setPendingTransactions(pendingTransactions);
        newBlock.setPrevBlockHash(previousBlockHash);
        newBlock.setIndex(index + 1);
        return newBlock;


    }

}
