package com.blockchain.node.staticdata;

import com.blockchain.node.data.Block;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringWriter;

public class GetJSONData {

    /***
     *
     * @return
     * @throws IOException
     */
    public String JSONStringParser() throws IOException {


        JSONObject jsonObj = new JSONObject();

        jsonObj.put("test", "test");
        jsonObj.put("test", new Integer(100));
        jsonObj.put("test", new Double(100));

        StringWriter stringWriter = new StringWriter();
        jsonObj.writeJSONString(stringWriter);

        String jsonObjToString = stringWriter.toString();

        return jsonObjToString;

    }

    public void implementNewBlockData(String JSONData){

        Block implBlock = new Block();
        System.out.printf("This is the JSON data from the Postnam" + JSONData);

    }
}
