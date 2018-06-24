package com.blockchain.network.wallet;

import com.blockchain.network.wallet.keyhelper.KeyGeneratorHelper;
import com.blockchain.node.data.Wallet;
import org.bouncycastle.math.ec.ECPoint;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

@Controller
public class OpenWalletContorller {

    private String privateKeyStr;

    @RequestMapping(method = RequestMethod.GET, value = "/wallet/openwallet")
    public String openwallet(Model model) {
        model.addAttribute("wallet", new Wallet());

        return "openwallet";
    }

    @PostMapping("/wallet/openwalletresult")
    public String openwalletResult(@ModelAttribute Wallet wallet, Model model) throws UnsupportedEncodingException {

        this.privateKeyStr = wallet.getFrom();
        System.out.println(wallet.getFrom());
        KeyGeneratorHelper helper = new KeyGeneratorHelper();

        BigInteger privateKey = new BigInteger(wallet.getFrom().getBytes());

        ECPoint pubKey = helper.getPublicKeyFromPrivateKey(privateKey);
        String pubKeyCompressed = helper.encodeECPointHexCompressed(pubKey);

        String addr = helper.calcRipeMD160(pubKeyCompressed);
        System.out.println(addr);
        System.out.println("here is the last");

        model.addAttribute("addr", addr);
        return "openwalletresult";
    }

}
