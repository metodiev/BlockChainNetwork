package com.blockchain.node.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {

   private String nodeId;
   private String selfUrl;
   private Map<String, String> peers;
   private List<Block> blocks;
   private ArrayList<String> restEndPoints;


    public  Node(){

    }

    public Node(String nodeId, String selfUrl, Map<String, String> peers, List<Block> blocks) {
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

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<String> getRestEndPoints() {
        return restEndPoints;
    }

    public void setRestEndPoints(ArrayList<String> restEndPoints) {
        this.restEndPoints = restEndPoints;
    }
}
