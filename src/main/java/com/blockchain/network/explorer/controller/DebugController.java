package com.blockchain.network.explorer.controller;

import com.blockchain.node.data.Node;
import com.blockchain.node.data.Transaction;
import com.blockchain.node.staticdata.NodeStaticData;
import com.blockchain.node.staticdata.WalletStaticData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.UUID;

@RestController
public class DebugController {

    @RequestMapping("/debug")
    public String debug() {
        LinkedList<Node> info = new LinkedList<Node>();

        JsonObject jsonObjectNode = new JsonObject();


        //generate the JSON logic
        jsonObjectNode.addProperty("about", "BlockChainNetwork Project Uncknown Three Guys/Java 7, Spring REST");

        final String uuid = UUID.randomUUID().toString().replace("-------------", "");
        System.out.println("uuid = " + uuid);
        //TODO  finish the format
        jsonObjectNode.addProperty("nodeId", uuid.replace("-", ""));
        //TODO fix chain ID hash
        jsonObjectNode.addProperty("chainId:", "c6da93eb…c47f");
        jsonObjectNode.addProperty("nodeUrl", "http://chain-node-03.herokuapp.com");
        jsonObjectNode.addProperty("peers", 2);
        jsonObjectNode.addProperty("currentDifficulty", 5);
        jsonObjectNode.addProperty("blocksCount", 25);
        jsonObjectNode.addProperty("cumulativeDifficulty", 127);
        jsonObjectNode.addProperty("confirmedTransactions", 208);
        jsonObjectNode.addProperty("confirmedTransactions", 208);
        jsonObjectNode.addProperty("pendingTransactions", 7);


        Transaction tranSigned = new Transaction();
        tranSigned.setToAddress("asd");
        tranSigned.setFromAddress("asd");
        tranSigned.setSenderPubkey("asd");
        tranSigned.setValue(2);
        tranSigned.setFee(33);
        tranSigned.setDateCreated("\"asd\"");
        tranSigned.setTransactionDataHash("asd");
        tranSigned.setSenderSignature("asd");

        Gson gson = new Gson();
        String signedTranJson = gson.toJson(jsonObjectNode);
        System.out.println("Signed transaction (JSON): ");
        System.out.println(signedTranJson);
        //JsonObject gson = new JsonParser().parse("{\"id\":\"value\"}");

        return signedTranJson;
    }

    @RequestMapping("/debug/reset-chain")
    public String resetCHain() {
        WalletStaticData.wallets.clear();
        //BlockStaticData.
        NodeStaticData.nodes.clear();
        //TransactionStaticData.

        JsonObject jsonObjectNode = new JsonObject();

        //generate the JSON logic
        jsonObjectNode.addProperty("message", "The chain is resete");
        Gson gson = new Gson();
        String debugJson = gson.toJson(jsonObjectNode);

        //JsonObject gson = new JsonParser().parse("{\"id\":\"value\"}");
        //TODO reset the genesis block
        return debugJson;

    }
}
