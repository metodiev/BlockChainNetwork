package com.blockchain.network.explorer.controller.web;

import com.blockchain.node.data.Wallet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class OpenWalletContorller {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Home Controller";

    @RequestMapping("/wallet/openwallet")
    public String welcome(Map<String, Object> model) {
        model.put("home", this.message);
        return "openwallet";
    }

   /* @GetMapping("/wallet/openwallet")
    public String greetingForm(Model model) {
        model.addAttribute("wallet", new Wallet());
        return "wallet";
    }

    @PostMapping("/wallet/openwallet")
    public String greetingSubmit(@ModelAttribute Wallet wallet) {
        return "result";
    }*/
}
