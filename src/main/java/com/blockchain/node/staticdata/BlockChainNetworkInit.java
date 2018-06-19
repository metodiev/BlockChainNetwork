package com.blockchain.node.staticdata;

import com.blockchain.node.core.BlockConnector;
import com.blockchain.node.core.NodeConnector;
import com.blockchain.node.data.Block;
import com.blockchain.node.data.Node;

import java.util.ArrayList;
import java.util.List;

public class BlockChainNetworkInit {

    private static List<Block> blockChainNetworkBlocks = new ArrayList<Block>();
    private static List<Node> blockChainNetworkNode = new ArrayList<Node>();


    public static void init(){

        //initialise the genesis block
        BlockConnector blockConnector = new BlockConnector();

        blockChainNetworkBlocks.add(blockConnector.implementGenesisBlock());


        //implementation of the Node data
        NodeConnector nodeConnector = new NodeConnector();
       /* nodeConnector.implementNode();
        blockChainNetworkNode.add();*/

       //



    }

    public void getBlockFromJSON(){
        GetJSONData json = new GetJSONData();

    }

}
