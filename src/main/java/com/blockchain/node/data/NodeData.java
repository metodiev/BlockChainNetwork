package com.blockchain.node.data;

import com.blockchain.node.Block;

import java.util.LinkedList;

public class NodeData {
     private String nodeId;
     private  String selfURL ;
     private String peers ;
     private LinkedList <Block> chain;
}
