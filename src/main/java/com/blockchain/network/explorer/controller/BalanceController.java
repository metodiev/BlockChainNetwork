package com.blockchain.network.explorer.controller;

import com.blockchain.node.data.Transaction;
import com.blockchain.node.data.Wallet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {
    @RequestMapping("/address/balance")
    public  String Balances ()
    {
        Wallet wallet = new Wallet();
        JsonObject jsonObjectNode = new JsonObject();
        String blockJson = "";
        Gson gson = new Gson();
        boolean isEmpty = true;

        if(wallet.getFrom()==null)
        {
            isEmpty = false;


        }
        if(!isEmpty)
        {
            jsonObjectNode.addProperty("errorMsg" ,"Invalid address");

        }  else {
            jsonObjectNode.addProperty("from",wallet.getFrom());
            jsonObjectNode.addProperty("to",wallet.getTo());
            jsonObjectNode.addProperty("fee",wallet.getValue());
            jsonObjectNode.addProperty("fromKey",wallet.getFee());

        }

        blockJson = gson.toJson(jsonObjectNode);


        return  blockJson;
    }
}
