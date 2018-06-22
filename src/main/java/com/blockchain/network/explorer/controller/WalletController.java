package com.blockchain.network.explorer.controller;

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
        boolean transferSuccessful = (boolean)data.get("transferSuccessful");

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




    @RequestMapping(value = "/createWallet", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createWalletFromJson (@RequestBody String  jsonData) {

        validateJsonWallet(jsonData);

        return jsonData;
    }

    //-Create wallet - makes the user stuff that is PrivateKEy and publickey and arrdess
    //-openwallet-user puts his private key and from there we can generate the public key
    //-create transaction = user puts the toAddress, value,data(optional) and fee  and in the transaction
    // the program should put the the public key and fromAddress
    //-sign the tx - the TxData should be generated and then with that data and with private key
    //the signature should be generated
    //-after the signing the signature and txData should be appended to the TX and we can send it to the NODE

    public boolean validateJsonWallet(String jsonData){


        JSONObject data = (JSONObject)JSONValue.parse(jsonData);
        String from = (String)data.get("from").toString().replace(" ", "");
        String to = (String)data.get("to").toString().replace(" ", "");
        long value = (long) data.get("value");

        String dateCreated = (String)data.get("dateCreated");
        String senderPubKey = (String)data.get("senderPubKey");
        String transactionDataHash = (String)data.get("transactionDataHash");
        String senderSignature = (String)data.get("senderSignature");
        long  fee = (long)data.get("fee");
        String dataOptional = (String)data.get("data");
        if (data.isEmpty()){

            WalletConnector walletConnector = new WalletConnector();
            walletConnector.implementWallet(fee, from, senderPubKey, to, value);
        }else {

            WalletConnector walletConnector = new WalletConnector();
            walletConnector.implementWallet(fee, from, senderPubKey, to, value, dataOptional);
        }

        boolean isValid = false;



    return isValid;
    }

}


