package com.blockchain.network.explorer;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component("blockController")
@RestController
public class BlockController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counter1 = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting() {
        return "asdfasdf";
    }


    @RequestMapping("/block")
    public String block() {
        return "this is block";
    }

    @RequestMapping("/node")
    public String node() {
        return "this is the node part";
    }


}