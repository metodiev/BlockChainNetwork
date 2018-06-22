package com.blockchain.node.core;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.blockchain.node.data.Transaction;
import javafx.util.converter.BigIntegerStringConverter;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

import java.security.SignatureException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.google.gson.Gson;
import sun.net.NetProperties;

import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

public class Test {

    static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters Domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
            curve.getN(), curve.getH());

   public  static void main (String [] args) throws UnsupportedEncodingException, NoSuchFieldException, NoSuchAlgorithmException, SignatureException {
        signAndVerifyTransaction("f51362b7351ef62253a227a77751ad9b2302f911",
                                25000, 10, "2018-02-10T17:53:48.972Z",
                            "7e4670ae70c98d24f3662c172dc510a085578b9ccc717e6c2f4e547edd960a34");



    }

    private static void signAndVerifyTransaction(String recipientAddress, long value, long fee, String iso8601,
                                                 String senderPrivKeyHex) throws UnsupportedEncodingException, NoSuchFieldException, NoSuchAlgorithmException, SignatureException {
        System.out.println("Generate sign a transaction");
        System.out.println("---------------------------");
        System.out.println("Sender private key:" + senderPrivKeyHex);
        BigInteger privateKey = new BigInteger(senderPrivKeyHex, 16);
        ECPoint pubKey = getPublicKeyFromPrivateKey(privateKey);
        String senderPubKeyCompressed = encodeECPointHexCompressed(pubKey);
        System.out.println("Public key (compressed):" + senderPubKeyCompressed);
        String senderAddress = calcRipeMD160(senderPubKeyCompressed);
        System.out.println("BlockChain address:" + senderAddress);

        Transaction tran = new Transaction();
        tran.setToAddress(recipientAddress);
        tran.setFromAddress(senderAddress);
        tran.setSenderPubkey(senderPubKeyCompressed);
        tran.setValue(value);
        tran.setFee(fee);
        GetCurrentDate getCurrentDate = new GetCurrentDate();
        tran.setDateCreated(iso8601);

//signAndVerifyTransaction("f51362b7351ef62253a227a77751ad9b2302f911",
// 25000, 10, "2018-02-10T17:53:48.972Z",
// "7e4670ae70c98d24f3662c172dc510a085578b9ccc717e6c2f4e547edd960a34");
//        //16
//        Transaction Transaction = new Transaction(
//                senderAddress,
//                recipientAddress,
//                senderPubKeyCompressed,
//                value,
//                fee,
//                iso8601datetime,
//                null
//        );

        Gson gson = new Gson();
        String tranJson = gson.toJson(tran);

        byte[] tranHash = calcSHA256(tranJson);
        System.out.println("Transaction hash(sha256):" + BytesToHex(tranHash));

        BigInteger [] tranSignatureBig = signData(privateKey, tranHash);
        System.out.println("Transaction signature: [" + tranSignatureBig[0].toString(16) + ","
                + tranSignatureBig[1].toString(16) + "]");
        String[] tranSignature = new String[] {
                tranSignatureBig[0].toString(16),
                tranSignatureBig[1].toString(16)
        };



        Transaction tranSigned = new Transaction();
        tranSigned.setToAddress(recipientAddress);
        tranSigned.setFromAddress(senderAddress);
        tranSigned.setSenderPubkey(senderPubKeyCompressed);
        tranSigned.setValue(value);
        tranSigned.setFee(fee);
        tranSigned.setDateCreated(iso8601);
        tranSigned.setTransactionDataHash(Hex.toHexString(tranHash));
        tranSigned.setSenderSignature(tranSignature[0]+ tranSignature[1]);

/*        Transaction tranSigned = new Transaction(
                senderAddress,
                recipientAddress,
                senderPubKeyCompressed,
                value,
                fee,
                iso8601datetime,
                tranHash,
                senderSignature );*/

        String signedTranJson = gson.toJson(tranSigned);
        System.out.println("Signed transaction (JSON): ");
        System.out.println(signedTranJson);

        //verifySignature(String privateKeyHex, BigInteger[] signature, byte[] msg)
        System.out.println(privateKey.toString(16));
        System.out.println("senderSignature: f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee912513069e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6");
        System.out.println("SIGNTARURTE:     "+tranSignature[1]+tranSignature[0]);
        System.out.println("HASH:"+BytesToHex(tranHash));

//message,addres,signature
        String  StringTranSign =  tranSignature[1]+ tranSignature[0];
       System.out.println(StringTranSign);
  //      signatureVerifier(BytesToHex(tranHash), StringTranSign, senderAddress);
     //   BigInteger[] signature = tranSignature.toString();
        //System.out.println(verifySignature(privateKey.toString(16),tranSignature,tranHash));
        System.out.println(verifySignature(privateKey.toString(16), tranSignatureBig, tranHash));
    }

    private static byte[] calcSHA256(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        SHA256Digest digest = new SHA256Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return result;

    }

    public static ECPoint getPublicKeyFromPrivateKey(BigInteger privKey) {
        ECPoint pubKey = curve.getG().multiply(privKey).normalize();
        return pubKey;
    }

    public static String encodeECPointHexCompressed(ECPoint point) {
        BigInteger x = point.getXCoord().toBigInteger();
        BigInteger y = point.getYCoord().toBigInteger();
        return x.toString(16) + (y.testBit(0) ? 1 : 0);
    }


    private static String calcRipeMD160(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return BytesToHex(result);
    }


    private static BigInteger[] signData(BigInteger privateKey, byte[] data) throws NoSuchAlgorithmException{
        ECDomainParameters ecSpec = new ECDomainParameters(curve.getCurve(), curve.getG(),
                curve.getN(), curve.getH());
        ECPrivateKeyParameters keyParameters = new ECPrivateKeyParameters(privateKey, ecSpec);

        DSAKCalculator kCalculator = new HMacDSAKCalculator(new SHA256Digest());
        ECDSASigner signer  = new ECDSASigner(kCalculator);
        signer.init(true, keyParameters);
        BigInteger[] signature = signer.generateSignature(data);

        return signature;
    }

    public static String BytesToHex(byte[] bytes) {
        return Hex.toHexString(bytes);
    }



    public static ECPublicKeyParameters toPublicKey(String privateKey) {
        BigInteger d = new BigInteger(privateKey, 16);
        ECPoint q = Domain.getG().multiply(d);
        ECPublicKeyParameters publicParams = new ECPublicKeyParameters(q, Domain);
        return publicParams;
    }
    public static boolean verifySignature(String privateKeyHex, BigInteger[] signature, byte[] msg) {
        DSAKCalculator kCalculator = new HMacDSAKCalculator(new SHA256Digest());
        ECDSASigner signer = new ECDSASigner(kCalculator);
        ECPublicKeyParameters pubKey = toPublicKey(privateKeyHex);
        signer.init(false, pubKey);

        return signer.verifySignature(msg, signature[0], signature[1]);
    }
    public static void signatureVerifier(String msg, String sign, String address) throws SignatureException, UnsupportedEncodingException {
        String[] separateInput = separateInput(sign) ;
     //   byte[] msgHash = Hash.sha3(msg.getBytes());

        Sign.SignatureData signature = new SignatureData((byte) 28, Numeric.hexStringToByteArray(separateInput[2]),
                Numeric.hexStringToByteArray(separateInput[1]));

        BigInteger pubKeyRecovered = Sign.signedMessageToKey(msg.getBytes(), signature);

        String strPubKeyRec = pubKeyRecovered.toString();
        //String senderPubKeyCompressed = encodeECPointHexCompressed(pubKeyRecovered);
     //   System.out.println("Public key (compressed):" + senderPubKeyCompressed);

        ECPoint w = ((ECPublicKey)pubKeyRecovered).getQ();

        String senderPubKeyCompressed = encodeECPointHexCompressed(w);
        System.out.println("pubKeyRecovered" +pubKeyRecovered);
        System.out.println("Public key (compressed):" + senderPubKeyCompressed);
        String senderAddress = calcRipeMD160(senderPubKeyCompressed);
        String addrFromPubKeyRecovered = Keys.getAddress(pubKeyRecovered);

        addrFromPubKeyRecovered = new String("0x" + addrFromPubKeyRecovered);

        System.out.println(addrFromPubKeyRecovered);

        boolean areEqualsAdresses = addrFromPubKeyRecovered.equals(address.toLowerCase());
        if (areEqualsAdresses) {
            System.out.println("valid");
        } else {
            System.out.println("invalid");
        }

    }
    public static String[] separateInput(String input) {

        String[] separateInput = input.split(",");
        //0xf121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130
        // 69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6
       // String v = separateInput[1].split(":")[1].replaceAll("\"", "");
       // String r = separateInput[2].split(":")[1].replaceAll("\"", "");
        //String s = separateInput[3].split(":")[1].replaceAll("\"}", "").replaceAll("\"", "").replaceAll("0x", "");

        String[] arr = new String[3];
        arr[0] = "0x";
        arr[1] = "f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130";
        arr[2] = "69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6";
        System.out.println("separateInput Output:"+arr[0]+arr[1]+arr[2]);
        return arr;
    }


}


/*     public void ChangeTheName(){
		String[] separateInput = separateInput(input) ;
		byte[] msgHash = Hash.sha3(msg.getBytes());

		Sign.SignatureData signature = new SignatureData((byte) 28, Numeric.hexStringToByteArray(separateInput[2]),
				Numeric.hexStringToByteArray(separateInput[1]));

		BigInteger pubKeyRecovered = Sign.signedMessageToKey(msg.getBytes(), signature);
		String address = Keys.getAddress(pubKeyRecovered);

		address = new String("0x" + address);
		System.out.println(address);

		boolean areEqualsAdresses = address.equals(AddressConst.toLowerCase());
		if (areEqualsAdresses) {
			System.out.println("valid");
		} else {
			System.out.println("invalid");
		}

	}

	public static String[] separateInput(String input) {

		String[] separateInput = input.split(",");
		String v = separateInput[1].split(":")[1].replaceAll("\"", "");
		String r = separateInput[2].split(":")[1].replaceAll("\"", "");
		String s = separateInput[3].split(":")[1].replaceAll("\"}", "").replaceAll("\"", "").replaceAll("0x", "");

		String[] arr = new String[3];
		arr[0] = v;
		arr[1] = s;
		arr[2] = r;

		return arr;
	}
	*/