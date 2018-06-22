package com.blockchain.network.explorer.controller;

import com.blockchain.node.core.NodeReceiveVerifySend;
import com.blockchain.node.data.Transaction;
import com.blockchain.node.staticdata.GetJSONData;
import com.blockchain.node.staticdata.TransactionStaticData;
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

import java.io.UnsupportedEncodingException;

@RestController
@Controller
public class TransactionController {

    @RequestMapping(value = "/transactions/pending", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String getJsonPendingTransaction (@RequestBody String  jsonData) throws UnsupportedEncodingException {
        GetJSONData json = new GetJSONData();

        if (addNewPendingTransaction(jsonData)){
            return jsonData;
        }else {
            String srrMsg = "(errorMsg, Invalid transaction: field is missing)";
            return srrMsg;
        }
    }

    private  boolean addNewPendingTransaction(String jsonData) throws UnsupportedEncodingException {

        JSONObject JSONdata = (JSONObject)JSONValue.parse(jsonData);

        String from = (String)JSONdata.get("from");
        String to = (String)JSONdata.get("to");
        long value = (long) JSONdata.get("value");
        long  fee = (long)JSONdata.get("fee");
        String dateCreated = (String)JSONdata.get("dateCreated");

        if(!(JSONdata.get("data").toString().isEmpty())) {
            String data = (String) JSONdata.get("data");
        }
        String senderPubKey = (String)JSONdata.get("senderPubKey");
        String transactionDataHash = (String)JSONdata.get("transactionDataHash");
        String  senderSignature = (String)JSONdata.get ("senderSignature");

        //sender signature
        JSONArray arrJson = (JSONArray) JSONdata.get("senderSignature");
        String  r = arrJson.get(0).toString();
        String s = arrJson.get(1).toString();

        Transaction transaction = new Transaction();
        transaction.setFromAddress(from);
        transaction.setToAddress(to);
        transaction.setValue(value);
        transaction.setDateCreated(dateCreated);
        transaction.setSenderPubkey(senderPubKey);
        transaction.setTransactionDataHash(transactionDataHash);
        transaction.setrValue(r);
        transaction.setsValue(s);

        TransactionStaticData.addNewTransaction(transaction);

       return transactionSingAndVerify(transaction);

    }

    private boolean transactionSingAndVerify(Transaction transaction) throws UnsupportedEncodingException {
        NodeReceiveVerifySend nodeReceiveVerifySend = new NodeReceiveVerifySend();
        boolean isValidTransaction = nodeReceiveVerifySend.verifyAndSendTransaction(transaction);

        return isValidTransaction;
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
