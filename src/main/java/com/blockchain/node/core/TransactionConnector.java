package com.blockchain.node.core;

import com.blockchain.node.Transaction;

import java.text.DateFormat;

public class TransactionConnector {

    public Transaction genesisTransaction (String fromAddress,int value,int blockIndex){
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

    public  void pendingTransaction (String fromAddress,String toAddress,int value, int fee,String dataHash,String senderSignature)
    {
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
    public Transaction confirmedTransaction(String from,String to,int value,int fee,String senderPubKey,String transactionDataHash,String senderSignature,int minedBlockIndex,boolean transactionSuccessfull)
    {
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
