package com.blockchain.network.explorer.controller;


import com.blockchain.node.data.Node;
import com.blockchain.node.data.Transaction;
import com.blockchain.node.staticdata.NodeStaticData;
import com.google.gson.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.UUID;


@RestController
@Controller
public class InfoController  {


  /*  { "about": "SoftUniChain/0.9-csharp",

            "nodeId": "1a22d3…9b2f",
             chainId: "c6da93eb…c47f",
            "nodeUrl": "http://chain-node-03.herokuapp.com",
            "peers": 2, "currentDifficulty": 5,
            "blocksCount": 25, "cumulativeDifficulty": 127
        "confirmedTransactions": 208, "pendingTransactions": 7

    }*/

    @RequestMapping("/info")
    public String info() {
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
        jsonObjectNode.addProperty("about", "BlockChainNetwork Project Unknown Three Guys/Java 7, Spring REST");

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



}
