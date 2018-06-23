package com.blockchain.network.explorer.controller.web;


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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;
import java.math.BigInteger;
import java.security.SecureRandom;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

//import static com.blockchain.node.core.RandomKeyPairGenerator.Domain;

@Controller
public class CreateNewWalletController {

    //wallet generator
    static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters Domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
            curve.getN(), curve.getH());
    // inject via application.properties


    @RequestMapping("/wallet/createwallet")
    public String welcome(Map<String, Object> model) {
        //   model.put("home", this.message);
        return "createwallet";
    }

    public  String encodeECPointHexCompressed(ECPoint point) {
        BigInteger x = point.getXCoord().toBigInteger();
        BigInteger y = point.getYCoord().toBigInteger();
        return x.toString(16) + (y.testBit(0) ? 1 : 0);
    }

    public static String BytesToHex(byte[] bytes) {
        return Hex.toHexString(bytes);
    }
    private static String calcRipeMD160(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return BytesToHex(result);
    }
    @GetMapping("/wallet/createwalletresult")
    public String walletSubmitForm(Model model) throws UnsupportedEncodingException {


        AsymmetricCipherKeyPair keyPair = generateRandomKeys();
        BigInteger privateKey = (((ECPrivateKeyParameters) keyPair.getPrivate()).getD());

        ECPoint pubKey = getPublicKeyFromPrivateKey(privateKey);
        String pubKeyCompressed = encodeECPointHexCompressed(pubKey);

        String addr = calcRipeMD160(pubKeyCompressed);

        model.addAttribute("privatekey", privateKey.toString(16));
        model.addAttribute("publickey", pubKey);
        model.addAttribute("blockchainaddress", addr);
        return "createwalletresult";
    }

   /* @PostMapping("/wallet/createwallet")
    public String walletSubmitResult(@ModelAttribute Wallet wallet) {


        System.out.println(wallet.getTo());
        return "createwalletresult";
    }*/

    public  org.bouncycastle.math.ec.ECPoint getPublicKeyFromPrivateKey(BigInteger privKey) {
        org.bouncycastle.math.ec.ECPoint pubKey = curve.getG().multiply(privKey).normalize();
        return pubKey;
    }


    private  AsymmetricCipherKeyPair generateRandomKeys() {
        ECKeyPairGenerator gen = new ECKeyPairGenerator();
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(Domain, secureRandom);
        gen.init(keyGenParam);
        return gen.generateKeyPair();

    }
}
