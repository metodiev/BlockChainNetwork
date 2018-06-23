package com.blockchain.network.explorer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@Controller
public class AccountBallanceController {

    @RequestMapping("/wallet/account")
    public String account(Map<String, Object> model) {
        return "account";
    }
}
