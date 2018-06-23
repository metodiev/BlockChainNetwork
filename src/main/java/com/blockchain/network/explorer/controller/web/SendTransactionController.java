package com.blockchain.network.explorer.controller.web;

import com.blockchain.node.data.Transaction;
import com.blockchain.node.data.Wallet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SendTransactionController {

    @GetMapping("/wallet/sendtransaction")
    public String sendtransaction(Model model) {
        model.addAttribute("Transaction", new Transaction());

        return "sendtransaction";
    }

    @PostMapping("/wallet/sendtransaction")
    public String sendtransactionSubmit(@ModelAttribute Transaction transaction) {
        return "sendtransactionresult";
    }
}
