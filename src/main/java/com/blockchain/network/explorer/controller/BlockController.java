package com.blockchain.network.explorer.controller;


import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.blockchain.node.data.Block;
import com.blockchain.node.staticdata.GetJSONData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import org.mortbay.util.ajax.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


/*@Component("blockController")
@RestController*/
@RestController
public class BlockController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counter1 = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting() {
        Block test = new Block();
        test.setIndex(1);
        return String.valueOf(test.getIndex());
    }


    @RequestMapping("/block/{id}")
    public String block(@PathVariable("id") long id) {

       /* {

            "index": 0,

                "transactions": […],

            "difficulty": 5,

                "prevBlockHash": "d279fa6a31ae4fb07cfd9cf7f35cc01f…3cf20a",

                "minedBy": "91c43337992580bca7d5f758d09e88f9b7032a65",

                "blockDataHash": "5d845cddcd4404ecfd5476fd6b1cf0e5…a80cd3",

                "nonce": 2455432,

                "dateCreated": "2018-02-01T23:23:56.337Z",

                "blockHash": "00000abf2f3d86d5c000c0aa7a425a6a4a65…cf4c"

        }, {"index": 1, …}, {"index": 2, …}, …]*/

        JsonObject jsonObjectNode = new JsonObject();
        //generate the JSON logic


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

   /* @RequestMapping(value = "/method2", method = RequestMethod.POST)
    @ResponseBody
    public String method2() {
        return "method2";
    }*/


    // Handles GET or POST Request
    @RequestMapping("/one")
    public @ResponseBody
    String handlerOne() {
        return "<h1>Inside handlerOne() Method Of MyController</h1>";
    }

    // Handles POST Request Only
    @RequestMapping(value = "/two", method = RequestMethod.POST)
    public @ResponseBody
    String handlerTwo() {
        return "<h1>Inside handlerTwo() Method Of MyController</h1>";
    }

    // Handles GET Request Only
    @RequestMapping(value = "/three", method = RequestMethod.GET)
    public @ResponseBody
    String handlerThree() {
        return "<h1>Inside handlerThree() Method Of MyController</h1>";
    }

    // Handles POST Request If The Request Header Contains 'content-type=application/x-www-form-urlencoded'
    @RequestMapping(value = "/four", method = RequestMethod.POST, headers = {"content-type=application/x-www-form-urlencoded"})
    public @ResponseBody
    String handlerFour() {
        return "<h1>Inside handlerFour() Method Of MyController</h1>";
    }

    // Handles POST Request If The Request Content Type Iis 'application/x-www-form-urlencoded'
    @RequestMapping(value = "/five", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public @ResponseBody
    String handlerFive(JSON parameter) {

        return "<h1>Inside handlerFive() Method Of MyController</h1>";
    }

    // Handles POST or GET Request And Produce Content Type Of "text/plain"
    @RequestMapping(value = "/six", produces = {MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody
    String handlerSix() {
        return "<h1>Inside handlerSix() Method Of MyController</h1>";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/foos")
    public @ResponseBody
    String findById(@RequestParam long id) {
        return findById(id);
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