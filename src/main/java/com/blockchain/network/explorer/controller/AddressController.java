package com.blockchain.network.explorer.controller;
import com.blockchain.node.data.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AddressController {
    @RequestMapping("/address/transactions")
    public String  AddressControll() {

        Transaction transaction = new Transaction();
        JsonObject jsonObjectNode = new JsonObject();
       String [] transcactions = new String[10];
       String blockJson = "";

       Gson gson = new Gson();

       boolean isEmpty = true;

                if(transaction.getToAddress()==null)
                {
                    isEmpty = false;


            }
            if(!isEmpty)
            {
                 jsonObjectNode.addProperty("errorMsg" ,"Invalid address");

            }  else {
            jsonObjectNode.addProperty("index",transaction.getInedInBlockIndex());
                jsonObjectNode.addProperty("from",transaction.getFromAddress());
                    jsonObjectNode.addProperty("to",transaction.getToAddress());
                    jsonObjectNode.addProperty("value",transaction.getValue());
                    jsonObjectNode.addProperty("dateCreated",transaction.getDateCreated());
                    jsonObjectNode.addProperty("data",transaction.getData());
                    jsonObjectNode.addProperty("senderPubKey",transaction.getSenderPubkey());
                    jsonObjectNode.addProperty("transactionDataHash",transaction.getTransactionDataHash());
                    jsonObjectNode.addProperty("senderSignature",transaction.getSenderSignature());
                    jsonObjectNode.addProperty("minedBlockIndex",transaction.getMinedBlockIndex());
                    jsonObjectNode.addProperty("transferSuccesfull",transaction.isTranserSuccessful());
                    jsonObjectNode.addProperty("difficulty",transaction.getDifficulty());
                    jsonObjectNode.addProperty("prevBlockHas",transaction.getPrevBlockHash());
                    jsonObjectNode.addProperty("minedBy",transaction.getMinedBy());

        }

        blockJson = gson.toJson(jsonObjectNode);


    return  blockJson;
    }


}
