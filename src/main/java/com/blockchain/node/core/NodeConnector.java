package com.blockchain.node.core;

import com.blockchain.node.data.Block;
import com.blockchain.node.data.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NodeConnector {


    //impelment the first Node

    /***
     *
     * @param blocks
     * @param nodeId
     * @param peers
     * @param restEndPoints
     * @param selfUrl
     * @return
     */
    public Node implementNode(List<Block> blocks, String nodeId, Map<String, String> peers, ArrayList<String> restEndPoints, String selfUrl) {
        Node node = new Node();

        node.setBlocks(blocks);
        node.setNodeId(nodeId);
        node.setPeers(peers);
        node.setRestEndPoints(restEndPoints);
        node.setSelfUrl(selfUrl);

        return  node;
    }

}
