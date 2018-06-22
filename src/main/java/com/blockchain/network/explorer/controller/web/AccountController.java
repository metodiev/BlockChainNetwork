package com.blockchain.network.explorer.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AccountController {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/wallet/account")
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "account";
    }
}
