package com.blockchain.node.data;

import com.blockchain.node.Block;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class Node {




    /*
      *
      *
    * */
   /* NodeId: unique_string
    SelfUrl: URL
    Peers: map(nodeId  URL)
    Chain: Blockchain
    REST Endpoints*/

   private String nodeId;
   private String selfUrl;
   private Map<String, String> peers;
   private LinkedList<Block> blocks;
   private ArrayList<String> restEndPoints;



    public Node(String nodeId, String selfUrl, Map<String, String> peers, LinkedList<Block> blocks) {
        this.nodeId = nodeId;
        this.selfUrl = selfUrl;
        this.peers = peers;
        this.blocks = blocks;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public Map<String, String> getPeers() {
        return peers;
    }

    public void setPeers(Map<String, String> peers) {
        this.peers = peers;
    }

    public LinkedList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(LinkedList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<String> getRestEndPoints() {
        return restEndPoints;
    }

    public void setRestEndPoints(ArrayList<String> restEndPoints) {
        this.restEndPoints = restEndPoints;
    }
}
