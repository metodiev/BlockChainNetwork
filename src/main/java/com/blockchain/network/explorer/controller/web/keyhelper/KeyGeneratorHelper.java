package com.blockchain.network.explorer.controller.web.keyhelper;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

public class KeyGeneratorHelper {

    //wallet generator
    static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters Domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
            curve.getN(), curve.getH());

    public  String encodeECPointHexCompressed(ECPoint point) {
        BigInteger x = point.getXCoord().toBigInteger();
        BigInteger y = point.getYCoord().toBigInteger();
        return x.toString(16) + (y.testBit(0) ? 1 : 0);
    }

    public static String BytesToHex(byte[] bytes) {
        return Hex.toHexString(bytes);
    }
    public   String calcRipeMD160(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return BytesToHex(result);
    }

    public AsymmetricCipherKeyPair generateRandomKeys() {
        ECKeyPairGenerator gen = new ECKeyPairGenerator();
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerationParameters keyGenParam = new ECKeyGenerationParameters(Domain, secureRandom);
        gen.init(keyGenParam);
        return gen.generateKeyPair();

    }

    public  ECPoint getPublicKeyFromPrivateKey(BigInteger privKey) {
        org.bouncycastle.math.ec.ECPoint pubKey = curve.getG().multiply(privKey).normalize();
        return pubKey;
    }
}
