package com.blockchain.network.explorer.controller.web;

import com.blockchain.node.data.Wallet;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

@Controller
public class OpenWalletContorller {


   private String privateKeyStr;


    @RequestMapping( method = RequestMethod.GET)
    public String welcome(Model model) {
        //   model.put("home", this.message);
        model.addAttribute("wallet", new Wallet());

        return "openwallet";
    }

    /*@GetMapping("/wallet/openwalletresult")
    public String walletSubmitForm(Model model) throws UnsupportedEncodingException {
        model.addAttribute("wallet", new Wallet());

        KeyGeneratorHelper helper = new KeyGeneratorHelper();

        BigInteger privateKey = new BigInteger(this.privateKeyStr.getBytes());

        ECPoint pubKey = helper.getPublicKeyFromPrivateKey(privateKey);
        String pubKeyCompressed = helper.encodeECPointHexCompressed(pubKey);

        String addr = helper.calcRipeMD160(pubKeyCompressed);

        model.addAttribute("privatekey", privateKey.toString(16));
        model.addAttribute("publickey", pubKey);
        model.addAttribute("blockchainaddress", addr);

        return "openwalletresult";
    }
*/
   @PostMapping("/wallet/openwalletresult")
    public String walletSubmitResult(@ModelAttribute Wallet wallet, Model model) throws UnsupportedEncodingException {

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
