package com.blockchain.network.explorer;

import com.blockchain.node.core.WalletConnector;
import com.blockchain.node.staticdata.GetJSONData;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
public class WalletController {

    @RequestMapping(value = "/postWalletData", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String handleJsonPostRequest (@RequestBody String  jsonData) {
        GetJSONData json = new GetJSONData();

        JSONObject data = (JSONObject)JSONValue.parse(jsonData);
        String from = (String)data.get("from").toString().replace(" ", "");
        String to = (String)data.get("to").toString().replace(" ", "");
        long value = (long) data.get("value");
        //int value = Integer.valueOf(valueTemp);
        String senderPubKey = (String)data.get("senderPubKey");
        long  fee = (long)data.get("fee");
        String dataOptional = (String)data.get("data");
        if (data.isEmpty()){
            //int fee, String from, String senderPubKey, String to, int value
            //implement the wallet
            //implement JSON to the java wallet
            WalletConnector walletConnector = new WalletConnector();
            walletConnector.implementWallet(fee, from, senderPubKey, to, value);
        }else {
            //int fee, String from, String senderPubKey, String to, int value
            //implement the wallet
            //implement JSON to the java wallet
            WalletConnector walletConnector = new WalletConnector();
            walletConnector.implementWallet(fee, from, senderPubKey, to, value, dataOptional);
        }



        return jsonData;
    }


   /* public static  void main(String [] args){
        String json = "{ \"from\": \"c3293572dbe6ebc60de4a20ed0e21446cae66b17\",\n" +
                "  \"to\": \"f51362b7351ef62253a227a77751ad9b2302f911\",\n" +
                "  \"value\": 25000, \"fee\": 10, \"dateCreated\": \"2018-02-10T17:53:48.972Z\",\n" +
                "  \"senderPubKey\": \"c74a8458cd7a7e48f4b7ae6f4ae9f56c5c88c0f03e7…bba1\"\n" +
                "}";

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
    }*/


    public boolean validateJsonWallet(){

        boolean isValid = false;



    return isValid;
    }

}


