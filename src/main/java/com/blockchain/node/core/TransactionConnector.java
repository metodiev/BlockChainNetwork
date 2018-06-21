package com.blockchain.node.core;

import com.blockchain.node.data.Transaction;

import java.text.DateFormat;

public class TransactionConnector {

    private String transactionDataHash;

    /***
     *
     * @param fromAddress
     * @param value
     * @param blockIndex
     * @return
     */
    public Transaction genesisTransaction(String fromAddress, int value, int blockIndex) {
        fromAddress = "0000000000000000000000000000000000000000";
        value = 1000;
        blockIndex = 0;
        Transaction transaction = new Transaction();
        transaction.setFromAddress(fromAddress);
        //TO DO SEND MONEY TO FAUCET  transaction.setToAddress("");
        transaction.setValue(value);
        transaction.setDateCreated(DateFormat.getDateInstance().toString());
        transaction.setData("genesis tx");
        transaction.setSenderPubkey(fromAddress);
        //TO DO SET TRANSACTION DATA HASHtransaction.setTransactionDataHash("");
        transaction.setInedInBlockIndex(blockIndex);
        //TO DO CONFIRM TRANSACTION transaction.setTranserSuccessful();

        return transaction;
    }

    /***
     * @param fromAddress
     * @param toAddress
     * @param value
     * @param fee
     * @param senderSignature
     */
    public void pendingTransaction(String fromAddress, String toAddress, long value, long fee, String data, String senderSignature,
                                   long inedInBlockIndex, boolean transerSuccessful,String s, String r) {



        Transaction transaction = new Transaction();


        //TO DO See if the money in the fauce Exceeds the money that are in transfers/exchange
        transaction.setFromAddress(fromAddress);
        transaction.setToAddress(toAddress);
        transaction.setValue(value);
        //TO DO FIND OUT THE RATE OF THE FEES
        transaction.setFee(fee);
        transaction.setDateCreated(DateFormat.getDateInstance().toString());
        transaction.setSenderPubkey(toAddress);
        transaction.setTransactionDataHash(transactionDataHash);
        //TO DO WHAT IS THAT HASH
        transaction.setSenderSignature(senderSignature);
        transaction.setrValue(r);
        transaction.setsValue(s);
        System.out.println("This is the transaction fee" + transaction.getFee());


    }

    public void pendingTransaction(String fromAddress, String toAddress, long value, long fee, String dataHash, String senderSignature, long minedInBlockIndex, boolean transferSuccessful) {
        Transaction transaction = new Transaction();
        //TO DO See if the money in the fauce Exceeds the money that are in transfers/exchange
        transaction.setFromAddress(fromAddress);
        transaction.setToAddress(toAddress);
        transaction.setValue(value);
        //TO DO FIND OUT THE RATE OF THE FEES
        transaction.setFee(fee);
        transaction.setDateCreated(DateFormat.getDateInstance().toString());
        transaction.setSenderPubkey(toAddress);
        transaction.setTransactionDataHash(dataHash);
        //TO DO WHAT IS THAT HASH
        transaction.setSenderSignature(senderSignature);

    }

    /***
     *
     * @param from
     * @param to
     * @param value
     * @param fee
     * @param senderPubKey
     * @param transactionDataHash
     * @param senderSignature
     * @param minedBlockIndex
     * @param transactionSuccessfull
     * @return
     */
    public Transaction confirmedTransaction(String from, String to, long value, long fee, String senderPubKey, String transactionDataHash, String  senderSignature, long minedBlockIndex, boolean transactionSuccessfull) {
        Transaction transaction = new Transaction();
        transaction.setFromAddress(from);
        transaction.setToAddress(to);
        transaction.setValue(value);
        transaction.setFee(fee);
        transaction.setDateCreated(DateFormat.getDateInstance().toString());
        transaction.setSenderPubkey(senderPubKey);
        transaction.setTransactionDataHash(transactionDataHash);
        transaction.setSenderSignature(senderSignature);
        transaction.setMinedBlockIndex(minedBlockIndex);
        transaction.setTranserSuccessful(transactionSuccessfull);

        return transaction;

    }



}
