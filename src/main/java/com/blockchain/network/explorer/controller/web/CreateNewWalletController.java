package com.blockchain.network.explorer.controller.web;


import com.blockchain.network.explorer.controller.web.keyhelper.KeyGeneratorHelper;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Map;

@Controller
public class CreateNewWalletController {

    @RequestMapping("/wallet/createwallet")
    public String createNewWallet(Map<String, Object> model) {
        return "createwallet";
    }

    @GetMapping("/wallet/createwalletresult")
    public String createNewtForm(Model model) throws UnsupportedEncodingException {

        KeyGeneratorHelper helper = new KeyGeneratorHelper();
        AsymmetricCipherKeyPair keyPair = helper.generateRandomKeys();
        BigInteger privateKey = (((ECPrivateKeyParameters) keyPair.getPrivate()).getD());

        ECPoint pubKey = helper.getPublicKeyFromPrivateKey(privateKey);
        String pubKeyCompressed = helper.encodeECPointHexCompressed(pubKey);

        String addr = helper.calcRipeMD160(pubKeyCompressed);

        model.addAttribute("privatekey", privateKey.toString(16));
        model.addAttribute("publickey", pubKey);
        model.addAttribute("blockchainaddress", addr);
        return "createwalletresult";
    }
}
