package com.blockchain.network.explorer.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class BallnceController {
    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Home Controller";

    @RequestMapping("/wallet/ballance")
    public String welcome(Map<String, Object> model) {
        model.put("home", this.message);
        return "ballance";
    }
}
