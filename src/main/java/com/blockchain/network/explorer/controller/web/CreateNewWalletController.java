package com.blockchain.network.explorer.controller.web;


import java.security.SecureRandom;

import com.blockchain.node.data.Wallet;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;



@Controller
public class CreateNewWalletController {

    //wallet generator
    static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters Domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
            curve.getN(), curve.getH());
    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Home Controller";

    @RequestMapping("/wallet/createwallet")
    public String welcome(Map<String, Object> model) {
        model.put("home", this.message);
        return "createwallet";
    }

    @GetMapping("/wallet/createwallet")
    public String walletSubmitForm(Model model) {

        model.addAttribute("wallet", new Wallet());

        return "createwallet";
    }

    @PostMapping("/wallet/createwallet")
    public String walletSubmitResult(@ModelAttribute Wallet wallet) {
        System.out.println(wallet.getFrom());
        System.out.println(wallet.getTo());
        return "createwalletresult";
    }

    private void getRandonKey(){
        generateRandomKeys();
    }

    private  AsymmetricCipherKeyPair generateRandomKeys() {
        ECKeyPairGenerator gen = new ECKeyPairGenerator();
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(Domain, secureRandom);
        gen.init(keyGenParam);
        return gen.generateKeyPair();

    }
}
