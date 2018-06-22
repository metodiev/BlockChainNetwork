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
public class SendTransactionController {

/*    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Home Controller";

    @RequestMapping("/wallet/sendtransaction")
    public String welcome(Map<String, Object> model) {
        model.put("home", this.message);
        return "sendtransaction";
    }*/

    @GetMapping("/wallet/sendtransaction")
    public String walletSubmitForm(Model model) {

        model.addAttribute("wallet", new Wallet());

        return "sendtransaction";
    }

    @PostMapping("/wallet/sendtransaction")
    public String walletSubmitResult(@ModelAttribute Wallet wallet) {
        System.out.println(wallet.getFrom());
        System.out.println(wallet.getTo());
        return "sendtransactionresult";
    }
}
