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

  /*  "selfUrl": "http://localhost:5555",
   "peers": { … },
            "chain": {
        "blocks": [{"index": 0, "transactions":[…], "difficulty": 0,
                "prevBlockHash": "d9…9c", "minedBy": "af…b2", "nonce": 0,
                "blockDataHash": "af25…d9", "dateCreated": "2018-01-…"},
        "blockHash": "c962…a8"}, {…}, {…}],
            "pendingTransactions": [{…}, …], "currentDifficulty": 5,
            "miningJobs": {"e3d8…5f": {…}, "25c1…a8": {…}, }
}, "confirmedBalances": {"2a7e…cf": 500020, … }*/

    @RequestMapping("/debug")
    public String debug() {
        LinkedList<Node> info = new LinkedList<Node>();


     /*   for (int i = 0; i < NodeStaticData.nodes.size(); i++) {

            Node node = new Node();
            node.setBlocks(NodeStaticData.nodes.get(i).getBlocks());
            node.setNodeId(NodeStaticData.nodes.get(i).getNodeId());
            node.setPeers(NodeStaticData.nodes.get(i).getPeers());
            node.setSelfUrl(NodeStaticData.nodes.get(i).getSelfUrl());

            System.out.println(NodeStaticData.nodes.get(i).getBlocks());
            info.add(node);
        }*/

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


        for (int i = 0; i < NodeStaticData.nodes.size(); i++) {

           /* Node node = new Node();
            node.setBlocks(NodeStaticData.nodes.get(i).getBlocks());
            node.setNodeId(NodeStaticData.nodes.get(i).getNodeId());
            node.setPeers(NodeStaticData.nodes.get(i).getPeers());
            node.setSelfUrl(NodeStaticData.nodes.get(i).getSelfUrl());

            System.out.println(NodeStaticData.nodes.get(i).getBlocks());
            info.add(node);*/


            //jsonObjectNode.put("blocks", NodeStaticData.nodes.get(i).getBlocks().get(i).getBlockDataHash());
        }

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
