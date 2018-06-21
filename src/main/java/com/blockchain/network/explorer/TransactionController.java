package com.blockchain.network.explorer;

import com.blockchain.node.core.TransactionConnector;
import com.blockchain.node.core.WalletConnector;
import com.blockchain.node.staticdata.GetJSONData;
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


   /* public static  void main(String [] args){
        String json = "{ \"from\": \"44fe0696beb6e24541cc0e8728276c9ec3af2675\"," +
                " \"to\": \"9a9f082f37270ff54c5ca4204a0e4da6951fe917\", " +
                "\"value\": 25000," +
                " \"fee\": 10, " +
                "\"dateCreated\": \"2018-02-10T17:53:48.972Z\", " +
                "\"data\": \"…\", " +
                "\"senderPubKey\": \"2a1d79fb8743d0a4a8501e0028079bcf82a4…eae1\"," +
                " \"transactionDataHash\": \"4dfc3e0ef89ed603ed54e47435a18…176a\"," +
                " \"senderSignature\": [\"e20c…a3c29df79f\", \"cf92…0acd0c2ffe56\"]," +
                " \"minedInBlockIndex\": 7," +
                " \"transferSuccessful\": true }";

        String jsonStr = "{\"BusName\":\"Joe\",\"BusPhone\":\"1234567890\"}";
        JSONObject myJsonObj = new JSONObject();

        String busname = myJsonObj.getOrDefault("BusPhone","asd").toString();
       // String busName = myJsonObj.getString("BusName");
        //String busPhone = myJSONObj.getString("BusPhone");
        System.out.println(busname);

        JSONObject data = (JSONObject)JSONValue.parse(jsonStr);
        String businessName = (String)data.get("BusName");
        String businessPhone = (String)data.get("BusPhone");
        System.out.println(businessName);
    }
*/

    public boolean validateJsonWallet(){

        boolean isValid = false;



        return isValid;
    }

}
