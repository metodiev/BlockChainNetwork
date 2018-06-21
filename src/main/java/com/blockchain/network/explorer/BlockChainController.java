package com.blockchain.network.explorer;

import com.blockchain.node.data.Block;
import com.blockchain.node.data.Node;
import com.blockchain.node.data.NodeData;
import com.blockchain.node.staticdata.NodeStaticData;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@Controller
public class BlockChainController {

    @RequestMapping("/info")
    public JSONObject info() {
        LinkedList<Node> info = new LinkedList<Node>();

        for (int i = 0; i < NodeStaticData.nodes.size(); i++) {

            Node node = new Node ();
            node.setBlocks(NodeStaticData.nodes.get(i).getBlocks());
            node.setNodeId(NodeStaticData.nodes.get(i).getNodeId());
            node.setPeers(NodeStaticData.nodes.get(i).getPeers());
            node.setSelfUrl(NodeStaticData.nodes.get(i).getSelfUrl());

            System.out.println(NodeStaticData.nodes.get(i).getBlocks());
            info.add(node);
        }

        JSONObject jsonObjectNode = new JSONObject();
        for (int i = 0; i < NodeStaticData.nodes.size(); i++) {

           /* Node node = new Node();
            node.setBlocks(NodeStaticData.nodes.get(i).getBlocks());
            node.setNodeId(NodeStaticData.nodes.get(i).getNodeId());
            node.setPeers(NodeStaticData.nodes.get(i).getPeers());
            node.setSelfUrl(NodeStaticData.nodes.get(i).getSelfUrl());

            System.out.println(NodeStaticData.nodes.get(i).getBlocks());
            info.add(node);*/


            jsonObjectNode.put("blocks", NodeStaticData.nodes.get(i).getBlocks().get(i).getBlockDataHash());
        }

        return  jsonObjectNode;
    }



}
