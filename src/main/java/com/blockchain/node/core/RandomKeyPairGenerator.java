package com.blockchain.node.core;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPairGeneratorSpi;
import java.security.SecureRandom;
import java.security.interfaces.ECKey;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.ECNamedCurveTable;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.cms.bc.BcKEKRecipientInfoGenerator;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

import org.bouncycastle.math.ec.ECPoint;

import java.security.SecureRandom;

public class RandomKeyPairGenerator {

    static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters Domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
            curve.getN(), curve.getH());

    public static void main(String[] args) throws UnsupportedEncodingException {
        //randomPrivateKeyToAddress();

    }

    @SuppressWarnings({"unused", "restriction"})
    private String calcRipeMD160(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return bytesToHex(result);
    }

    public AsymmetricCipherKeyPair generateRandomKeys() {
        ECKeyPairGenerator gen = new ECKeyPairGenerator();
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(Domain, secureRandom);
        gen.init(keyGenParam);
        return gen.generateKeyPair();

    }

    ////////////////////////////////
    public String encodeECPointHexCompressed(ECPoint point) {
        BigInteger x = point.getXCoord().toBigInteger();
        BigInteger y = point.getYCoord().toBigInteger();
        return x.toString(16) + (y.testBit(0) ? 1 : 0);
    }

    public String compressPubKey(BigInteger pubKey) {
        String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
        String pubKeyHex = pubKey.toString(16);
        String pubKeyX = pubKeyHex.substring(0, 64);
        return pubKeyYPrefix + pubKeyX;
    }

    ////////////public from private
    public BigInteger getPublicKeyFromPrivateKey(BigInteger privKey) {
        ECPoint pubKey = curve.getG().multiply(privKey).normalize();

        return pubKey.getXCoord().toBigInteger();
    }

    ///////////////////////////randomPrivateKeyToAddress
    private void randomPrivateKeyToAddress() throws UnsupportedEncodingException {

        System.out.println("Random private key --> public key --> address");
        System.out.println("---------------------------------------------");
        AsymmetricCipherKeyPair keyPair = generateRandomKeys();
        ///////////privateKey
        BigInteger privateKey = (((ECPrivateKeyParameters) keyPair.getPrivate()).getD());
        System.out.println("Private key (hex):" + privateKey.toString(16));
        System.out.println("Private key:" + privateKey.toString(10));
        ///////////publicKey
        ECPoint pubKey = ((ECPublicKeyParameters) keyPair.getPublic()).getQ();
        System.out.println("Public Key:(" + pubKey.getXCoord().toBigInteger().toString(10) + ","
                + pubKey.getYCoord().toBigInteger().toString(10) + ")");
        String pubKeyCompressed = encodeECPointHexCompressed(pubKey);
        System.out.println("Public key (compressed): " + pubKeyCompressed);
        /////Address
        String addr = calcRipeMD160(pubKeyCompressed);
        System.out.println("Blockchain address:" + addr);

    }


    public String getAdressFromPublicKey(ECPoint pubKey) throws UnsupportedEncodingException {

        String pubKeyCompressed = encodeECPointHexCompressed(pubKey);
        System.out.println("Public key (compressed): " + pubKeyCompressed);
        /////Address
        String addr = calcRipeMD160(pubKeyCompressed);
        System.out.println("Blockchain address:" + addr);
        return "";
    }

    public BigInteger getRadomPrivateKey() throws UnsupportedEncodingException {

        AsymmetricCipherKeyPair keyPair = generateRandomKeys();
        ///////////privateKey
        BigInteger privateKey = (((ECPrivateKeyParameters) keyPair.getPrivate()).getD());
        return privateKey;

    }

    public void getRadomPublicKey() throws UnsupportedEncodingException {
        AsymmetricCipherKeyPair keyPair = generateRandomKeys();
        ECPoint pubKey = ((ECPublicKeyParameters) keyPair.getPublic()).getQ();


        System.out.println("Public Key:(" + pubKey.getXCoord().toBigInteger().toString(10) + ","
                + pubKey.getYCoord().toBigInteger().toString(10) + ")");
        String pubKeyCompressed = encodeECPointHexCompressed(pubKey);
        System.out.println("Public key (compressed): " + pubKeyCompressed);


    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
