package com.blockchain.node.staticdata;

import com.blockchain.node.data.Node;
import java.util.ArrayList;
import java.util.List;

public class NodeStaticData {
    public static List<Node> nodes = new ArrayList<Node>();

    public static void addNewNode(Node node) {
        nodes.add(node);
    }

}

