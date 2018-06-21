package com.blockchain.node.mining;

import com.blockchain.node.core.GetCurrentDate;
import com.blockchain.node.data.Transaction;

import java.util.ArrayList;
import java.util.List;


public class CoinBaseTransaction {


 /*"from": "0000000000000000000000000000000000000000",
         "to": "9a9f082f37270ff54c5ca4204a0e4da6951fe917",
         "value": 5000350, "fee": 0,
          "dateCreated":
         "2018-02-10T17:53:48.972Z",
         "data": "coinbase tx",
         "senderPubKey": "000000000000000000000000000000000000…0000",
         "transactionDataHash": "4dfc3e0ef89ed603ed54e47435a18b…176a",
         "senderSignature": ["0000000000…0000", "0000000000…0000"],
         "minedInBlockIndex": 35,*/

  private static List<Transaction> coinBaseTransactions = new ArrayList<Transaction>();

  public void imolementCoinBaseTransaction(){

      Transaction transaction = new Transaction();
       transaction.setFromAddress("0000000000000000000000000000000000000000");
       transaction.setToAddress("9a9f082f37270ff54c5ca4204a0e4da6951fe917");
       transaction.setValue(5000350);
       transaction.setFee(0);

       //get current date
      GetCurrentDate date = new GetCurrentDate();
       transaction.setDateCreated(date.getCurrentdate());
       transaction.setSenderPubkey("000000000000000000000000000000000000");
       transaction.setTransactionDataHash("4dfc3e0ef89ed603ed54e47435a18b");
       transaction.setSenderSignature("0000000000");
       transaction.setMinedBlockIndex(35);
  }

  public static void main(String [] args){
      GetCurrentDate date = new GetCurrentDate();
      System.out.printf(date.getCurrentdate());
  }

}
