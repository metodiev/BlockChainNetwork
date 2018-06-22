package com.blockchain.node.staticdata;

import com.blockchain.node.data.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionStaticData {

    public static List<Transaction> transactions = new ArrayList<Transaction>();

    public static synchronized void  addNewTransaction(Transaction transaction){

        transactions.add(transaction);
    }
}

