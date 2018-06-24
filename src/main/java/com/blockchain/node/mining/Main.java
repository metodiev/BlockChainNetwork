package com.blockchain.node.mining;

import com.blockchain.node.data.Block;
import com.blockchain.node.data.Transaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void implementHardTransaction(){
        ArrayList<Block> blocks = new ArrayList<>();
        //create genesis block
        Transaction transaction = new Transaction();
        transaction.setFromAddress("Kiro send to miro money");
        transaction.setToAddress("miro address");
        ArrayList<Transaction> arrayListTrasactions = new ArrayList();
        arrayListTrasactions.add(transaction);

        Block genesisBLock = new Block( arrayListTrasactions, "0");
        System.out.println("genesis hash block " + genesisBLock.getBlockHash());

        //generate second block
        Transaction transactionSecond = new Transaction();
        transaction.setFromAddress("Miro send  money");
        transaction.setToAddress("kiro address");
        ArrayList<Transaction> arrayListTrasactions2 = new ArrayList();
        arrayListTrasactions2.add(transaction);
        Block block2 = new Block(arrayListTrasactions2, "10");

        System.out.println("second block  hash " +block2.getBlockHash());

        //generate third block

        Transaction transactionThird = new Transaction();
        transaction.setFromAddress("Miro send  money again");
        transaction.setToAddress("kiro address again");
        ArrayList<Transaction> arrayListTrasactions3 = new ArrayList();
        arrayListTrasactions3.add(transaction);
        Block block3 = new Block(arrayListTrasactions3, "1");

        System.out.println("third block  hash " +block3.getBlockHash());

    }

    public static void main(String [] args){

        implementHardTransaction();

    }

}
