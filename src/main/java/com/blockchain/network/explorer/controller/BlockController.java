package com.blockchain.network.explorer.controller;



import java.util.concurrent.atomic.AtomicLong;

import com.blockchain.node.data.Block;
import com.blockchain.node.staticdata.GetJSONData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlockController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counter1 = new AtomicLong();

    @RequestMapping("/block/{id}")
    public String block(@PathVariable("id") long id) {
        JsonObject jsonObjectNode = new JsonObject();

        //generate the JSON logic

        //TODO fix chain ID hash
        if (id == 1) {
            jsonObjectNode.addProperty("index:", "1");
            jsonObjectNode.addProperty("transactions", "http://chain-node-03.herokuapp.com");
            jsonObjectNode.addProperty("difficulty", 2);
            jsonObjectNode.addProperty("prevBlockHash", 5);
            jsonObjectNode.addProperty("minedBy", 25);
            jsonObjectNode.addProperty("blockDataHash", 127);
            jsonObjectNode.addProperty("nonce", 208);
            jsonObjectNode.addProperty("dateCreated", 208);
            jsonObjectNode.addProperty("blockHash", 7);

        }
        if (id == 2) {
            jsonObjectNode.addProperty("index:", "2");
            jsonObjectNode.addProperty("transactions", "http://chain-node-03.herokuapp.com");
            jsonObjectNode.addProperty("difficulty", 2);
            jsonObjectNode.addProperty("prevBlockHash", 5);
            jsonObjectNode.addProperty("minedBy", 25);
            jsonObjectNode.addProperty("blockDataHash", 127);
            jsonObjectNode.addProperty("nonce", 208);
            jsonObjectNode.addProperty("dateCreated", 208);
            jsonObjectNode.addProperty("blockHash", 7);
        }

        Gson gson = new Gson();
        String blockJson = gson.toJson(jsonObjectNode);

        //JsonObject gson = new JsonParser().parse("{\"id\":\"value\"}");
        //TODO reset the genesis block
        //TODO generate the blosk stufff and initialise the Block Logic
        //TODO The Genesis Block – The Start of the Chain
        return blockJson;
    }

    @RequestMapping("/node")
    public String node() {
        return "this is the node part";
    }


    @RequestMapping(value = "/postBlockData", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String handleJsonPostRequest(@RequestBody String jsonData) {
        GetJSONData json = new GetJSONData();

        json.implementNewBlockData(jsonData);
        System.out.println("saving user: " + json);

        return jsonData;
    }

}