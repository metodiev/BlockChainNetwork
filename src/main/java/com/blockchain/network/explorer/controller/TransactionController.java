package com.blockchain.network.explorer.controller;

import com.blockchain.node.core.TransactionConnector;
import com.blockchain.node.staticdata.GetJSONData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class TransactionController {

    @RequestMapping(value = "/postTransactionData", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String handleJsonPostRequest (@RequestBody String  jsonData) {
        GetJSONData json = new GetJSONData();

        JSONObject data = (JSONObject)JSONValue.parse(jsonData);

        String from = (String)data.get("from");
        String to = (String)data.get("to");
        long value = (long) data.get("value");
        //int value = Integer.valueOf(valueTemp);
        String senderPubKey = (String)data.get("senderPubKey");
        long  fee = (long)data.get("fee");
        String dateCreated = (String)data.get("dateCreated");
        String transactionDataHash = (String)data.get("transactionDataHash");
        String  senderSignature = (String)data.get ("senderSignature");

       // String r = senderSignature.substring(0,55);
      //  String s = senderSignature[1];
        long minedInBlockIndex = (long)data.get("minedInBlockIndex");
        boolean transferSuccessful = (boolean)data.get("transferSuccessful");
        //test Json array
        JSONArray arrJson = (JSONArray) data.get("senderSignature");
       String  r = arrJson.get(0).toString();
        String s = arrJson.get(1).toString();
     /*   String [] senderSignature = new String[arrJson.size()];
        for (int i = 0; i < arrJson.size(); i++) {
            senderSignature[i] = arrJson.get(i).toString();
            System.out.println(senderSignature[i]);
        } */



        //int fee, String from, String senderPubKey, String to, int value- DONE
        //implement the wallet
        //implement JSON to the java wallet
        TransactionConnector transactionConnector = new TransactionConnector();
        transactionConnector.pendingTransaction(from, to, value, fee, transactionDataHash, senderSignature, minedInBlockIndex, transferSuccessful,r ,s );

        return jsonData;

    }

    @RequestMapping("/transaction/pending")
    public JsonArray pendingTransaction() {

             /*  [{

"from": "44fe0696beb6e24541cc0e8728276c9ec3af2675",

"to": "9a9f082f37270ff54c5ca4204a0e4da6951fe917",

"value": 25000, "fee": 10,

"dateCreated": "2018-02-10T17:53:48.972Z", "data": "…",

"senderPubKey": "2a1d79fb8743d0a4a8501e0028079bcf82a4f…eae1",

"transactionDataHash": "4dfc3e0ef89ed603ed54e47435a18…176a",

"senderSignature": ["e20c…a3c29df79f", "cf92…0acd0c2ffe56"]

},*/

        JsonObject jsonObjectNode = new JsonObject();


        //generate the JSON logic

        jsonObjectNode.addProperty("from", "44fe0696beb6e24541cc0e8728276c9ec3af2675");
        jsonObjectNode.addProperty("to", "9a9f082f37270ff54c5ca4204a0e4da6951fe917");
        jsonObjectNode.addProperty("value", 25000);
        jsonObjectNode.addProperty("dateCreated", "2a1d79fb8743d0a4a8501e0028079bcf82a4f");
        jsonObjectNode.addProperty("senderPubKey", "T4dfc3e0ef89ed603ed54e47435a18");
        jsonObjectNode.addProperty("transactionDataHash", "4dfc3e0ef89ed603ed54e47435a18");



        JsonArray valueArray=new JsonArray();

            JsonObject jsonPropValue=new JsonObject();
            jsonPropValue.addProperty("id", "asdfasdfasdfasd32324");
            jsonPropValue.addProperty("propValue","asdfasdfasdf");
            valueArray.add(jsonPropValue);


        //jsonObjectNode.getAsJsonArray(valueArray.get(1).getAsString());

        Gson gson = new Gson();
        String transactionJson = gson.toJson(jsonObjectNode);

        //JsonObject gson = new JsonParser().parse("{\"id\":\"value\"}");
        //TODO reset the genesis block
        return valueArray.getAsJsonArray();
    }


    public boolean validateJsonWallet(){

        boolean isValid = false;



        return isValid;
    }

}
