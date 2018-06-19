package com.blockchain.network.explorer;


import java.util.concurrent.atomic.AtomicLong;

import com.blockchain.node.data.Block;
import com.blockchain.node.staticdata.GetJSONData;
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
@Controller
public class BlockController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counter1 = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting() {
        Block test = new Block();
        test.setIndex(1);
        return  String.valueOf(test.getIndex());
    }


    @RequestMapping("/block")
    public String block() {
        return "this is block";
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
    public @ResponseBody String handlerOne() {
        return "<h1>Inside handlerOne() Method Of MyController</h1>";
    }

    // Handles POST Request Only
    @RequestMapping(value = "/two", method = RequestMethod.POST)
    public @ResponseBody String handlerTwo() {
        return "<h1>Inside handlerTwo() Method Of MyController</h1>";
    }

    // Handles GET Request Only
    @RequestMapping(value = "/three", method = RequestMethod.GET)
    public @ResponseBody String handlerThree() {
        return "<h1>Inside handlerThree() Method Of MyController</h1>";
    }

    // Handles POST Request If The Request Header Contains 'content-type=application/x-www-form-urlencoded'
    @RequestMapping(value = "/four", method = RequestMethod.POST, headers = {"content-type=application/x-www-form-urlencoded"})
    public @ResponseBody String handlerFour() {
        return "<h1>Inside handlerFour() Method Of MyController</h1>";
    }

    // Handles POST Request If The Request Content Type Iis 'application/x-www-form-urlencoded'
    @RequestMapping(value = "/five", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public @ResponseBody String handlerFive(JSON parameter) {

        return "<h1>Inside handlerFive() Method Of MyController</h1>";
    }

    // Handles POST or GET Request And Produce Content Type Of "text/plain"
    @RequestMapping(value = "/six", produces = {MediaType.TEXT_PLAIN_VALUE})
    public @ResponseBody String handlerSix() {
        return "<h1>Inside handlerSix() Method Of MyController</h1>";
    }

    @RequestMapping(method=RequestMethod.POST, value="/foos")
    public @ResponseBody String findById(@RequestParam long id) {
        return findById(id);
    }

    @RequestMapping(value = "/postBlockData", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String handleJsonPostRequest (@RequestBody String  jsonData) {
        GetJSONData json = new GetJSONData();

        json.implementNewBlockData(jsonData);
        System.out.println("saving user: "+json);

        return jsonData;
    }

}