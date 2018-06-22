package com.blockchain.node.core;


import com.blockchain.node.data.Transaction;
import com.blockchain.node.staticdata.TransactionStaticData;
import com.google.gson.Gson;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.DSAKCalculator;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SignatureException;

import static com.blockchain.node.core.Test.encodeECPointHexCompressed;
import static com.sun.xml.internal.messaging.saaj.packaging.mime.util.ASCIIUtility.getBytes;

/*
For each received transaction the Node does the following:
    Checks for missing / invalid fields / invalid field values
    Calculates the transaction data hash (unique transaction ID)
    Checks for collisions  duplicated transactions are skipped
    Validates the transaction public key, validates the signature
    Checks the sender account balance to be >= value + fee
    Checks whether value >= 0 and fee > 10 (min fee)
    Puts the transaction in the "pending transactions" pool
    Sends the transaction to all peer nodes through the REST API
    It goes from peer to peer until it reaches the entire network

*/
public class NodeReceiveVerifySend {
    static final X9ECParameters curve = SECNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters domain = new ECDomainParameters(curve.getCurve(), curve.getG(),
            curve.getN(), curve.getH());
    private Transaction transaction;

    /*    public String hardCodedJson =    {
                "fromAddress":"c3293572dbe6ebc60de4a20ed0e21446cae66b17",
                    "toAddress":"f51362b7351ef62253a227a77751ad9b2302f911",
                    "value":25000,
                    "fee":10,
                    "dateCreated":"2018-02-10T17:53:48.972Z",
                    "senderPubkey":"c74a8458cd7a7e48f4b7ae6f4ae9f56c5c88c0f03e7c59cb4132b9d9d1600bba1",
                    "transactionDataHash":"930bfd70a191d6f0e90cc0c34d379b70e715f009e40925130431c305aed384d7",
                    "senderSignature":"69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130",
                    "inedInBlockIndex":0,
                    "transerSuccessful":false,
                    "minedBlockIndex":0
            }*/

    public static void main(String[] args) throws UnsupportedEncodingException, SignatureException {
        NodeReceiveVerifySend nodeReceiveVerifySend = new NodeReceiveVerifySend();
        nodeReceiveVerifySend.hardCodedTransactionData();
    }


    public void hardCodedTransactionData() throws UnsupportedEncodingException, SignatureException {
        Transaction transaction = new Transaction();
        transaction.setFromAddress("c3293572dbe6ebc60de4a20ed0e21446cae66b17");
        transaction.setToAddress("f51362b7351ef62253a227a77751ad9b2302f911");
        transaction.setValue(25000);
        transaction.setFee(10);
        transaction.setSenderPubkey("c74a8458cd7a7e48f4b7ae6f4ae9f56c5c88c0f03e7c59cb4132b9d9d1600bba1");
        transaction.setDateCreated("2018-02-10T17:53:48.972Z");
        transaction.setTransactionDataHash("930bfd70a191d6f0e90cc0c34d379b70e715f009e40925130431c305aed384d7");
        transaction.setSenderSignature("69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130");
        transaction.setrValue("69e32856edc1c95eeada2f0d03767ec4e1baaa75d6518ba5c82493039f6416d6");
        transaction.setsValue("f121ea497e35c07c7f27cb5b76e0fc23ba6224c288557c790cce5afee9125130");
        // verifyAndSendTransaction(transaction);
        //   Validates the transaction public key, validates the signature
        //  verifyPubKey(,transaction.getSenderPubkey());
        System.out.println(verifyTxHash(transaction,transaction.getTransactionDataHash()));
        // verifyAddress(transaction);
        // verify(byte[] hash, byte[] signature, byte[] publicKey)
        // boolean b =  verify();
        //System.out.println(b);
    }

    //fot he example its hard coded in the real life we'll get them from the wallet somehow


    public boolean verifyAndSendTransaction(Transaction transaction) throws UnsupportedEncodingException {

        // transaction = TransactionStaticData.transactions.get(0);
        String returnString = "";

        boolean isVlaidData = checkTransactionFieldAndData(transaction);
        if (isVlaidData) {
            boolean isValid = verifyTxHash(transaction, transaction.getTransactionDataHash());
            System.out.println("is the transsaction valid: " + isValid);

            return true;

        } else {
            return false;
        }

    }

    // Checks for missing / invalid fields / invalid field values
    private static boolean checkTransactionFieldAndData(Transaction transaction) {
        boolean isVlaidFieldsInputTransacion = false;

        boolean switchValue = true;
        switch (1) {
            case 1:
                isVlaidFieldsInputTransacion = transaction.getFromAddress().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getToAddress().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = String.valueOf(transaction.getValue()).isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getDateCreated().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getSenderPubkey().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getTransactionDataHash().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getSenderSignature().isEmpty();
                if (isVlaidFieldsInputTransacion) {
                    break;
                }
            case 2:
                isVlaidFieldsInputTransacion = transaction.getFromAddress().length() == 40;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getToAddress().length() == 40;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getValue() > 0;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getFee() >= 10;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getDateCreated().length() >= 6;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getSenderPubkey().length() == 66;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getTransactionDataHash().length() == 65;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getrValue().length() == 65;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }

                isVlaidFieldsInputTransacion = transaction.getsValue().length() == 65;
                if (isVlaidFieldsInputTransacion) {
                    break;
                }
        }

        return isVlaidFieldsInputTransacion;
    }

    public boolean verifyTxHash(Transaction transaction, String txDataHash) throws UnsupportedEncodingException {
        Transaction tran = new Transaction();
        tran.setToAddress(transaction.getToAddress());
        tran.setFromAddress(transaction.getFromAddress());
        tran.setSenderPubkey(transaction.getSenderPubkey());
        tran.setValue(transaction.getValue());
        tran.setFee(transaction.getFee());
        tran.setDateCreated(transaction.getDateCreated());
        Gson gson = new Gson();
        String tranJson = gson.toJson(tran);
        byte[] tranHash = calcSHA256(tranJson);
        if (BytesToHex(tranHash).equals(txDataHash)) {
            return true;
        } else {
            return false;
        }
    }
    private static String calcRipeMD160(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return BytesToHex(result);
    }
    public boolean verify(byte[] hash, byte[] signature, byte[] publicKey) {
        ASN1InputStream asn1 = new ASN1InputStream(signature);
        try {
            ECDSASigner signer = new ECDSASigner();
            signer.init(false, new ECPublicKeyParameters(curve.getCurve().decodePoint(publicKey), domain));

            DLSequence seq = (DLSequence) asn1.readObject();
            BigInteger r = ((ASN1Integer) seq.getObjectAt(0)).getPositiveValue();
            BigInteger s = ((ASN1Integer) seq.getObjectAt(1)).getPositiveValue();
            return signer.verifySignature(hash, r, s);
        } catch (Exception e) {
            return false;
        } finally {
            try {
                asn1.close();
            } catch (IOException ignored) {
            }
        }
    }

   /* public static boolean verifySignature(ECPoint publicKey, BigInteger[] signature, byte[] msg) {
        ECDSASigner signer = getSigner();
        signer.init(false, new ECPublicKeyParameters(publicKey, ecDomain));
        return signer.verifySignature(msg, signature[0], signature[1]);
    }*/
/*    private boolean verifyAddress(Transaction transaction) throws SignatureException, UnsupportedEncodingException {



        String pubKey = transaction.getSenderPubkey();
       // String pubKeyCompressed = encodeECPointHexCompressed((ECPoint.class.cast(pubKey.getBytes())));
       // System.out.println("Public key (compressed): " + pubKeyCompressed);

        String addr = ECPublicKeyParameterscalcRipeMD160(pubKey);
        System.out.println("Blockchain address:" + addr);


        String addr1 = calcRipeMD160(pubKey);
        System.out.println("sender address:" + transaction.getFromAddress());


        Sign.SignatureData signature = new Sign.SignatureData((byte) 28, Numeric.hexStringToByteArray(transaction.getrValue()),
                Numeric.hexStringToByteArray(transaction.getsValue()));
        BigInteger pubKeyRecovered = Sign.signedMessageToKey(transaction.getTransactionDataHash().getBytes(),signature);
        System.out.println("recoveredPubKey"+pubKeyRecovered.toString(16));
        String pubKeyRecoveredString = Hex.toHexString(pubKeyRecovered.toByteArray());
        System.out.println(pubKeyRecoveredString);

     //   String addr2 = calcRipeMD160(pubKeyRecovered.getBytes());
        String addr2 = calcRipeMD160(pubKeyRecoveredString);

       System.out.println("Blockchain address:" + addr2);
        String address = Keys.getAddress(pubKeyRecovered);
       System.out.println("Blockchain address:" + address.getBytes());
*//*
* 	public static String[] separateInput(String input) {

		String[] separateInput = input.split(",");
		String v = separateInput[1].split(":")[1].replaceAll("\"", "");
		String r = separateInput[2].split(":")[1].replaceAll("\"", "");
		String s = separateInput[3].split(":")[1].replaceAll("\"}", "").replaceAll("\"", "").replaceAll("0x", "");

		String[] arr = new String[3];
		arr[0] = v;
		arr[1] = s;
		arr[2] = r;

		return arr;
	}*//*;
     *//*
        String input = "{\"signature\":\"0xacd0acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b01\",\"v\":\"0x1\",\"r\":\"0xacd0acd4eabd1bec05393b33b4018fa38b69eba8f16ac3d60eec9f4d2abc127e\",\"s\":\"0x3c92939e680b91b094242af80fce6f217a34197a69d35edaf616cb0c3da4265b\"}";
        String[] separateInput = separateInput(input);

        String msg = "exercise-cryptography";
        byte[] msgHash = Hash.sha3(msg.getBytes());

        Sign.SignatureData signature = new SignatureData((byte) 28, Numeric.hexStringToByteArray(separateInput[2]),
                Numeric.hexStringToByteArray(separateInput[1]));


        System.out.println("0x" + address);
        *//*


        //  System.out.println("Sender private key:" + senderPrivKeyHex);
        //  BigInteger privateKey = new BigInteger(senderPrivKeyHex, 16);

*//*        BigInteger pubKeyRecovered = Sign.signedMessageToKey(msgHash, signature);
        String address = Keys.getAddress(pubKeyRecovered);

        ECPoint pubKeyEcPoint = new BigInteger(pubKey.getBytes());

        String senderPubKeyCompressed = encodeECPointHexCompressed(pubKey);
        System.out.println("Public key (compressed):" + senderPubKeyCompressed);
        String senderAddress = calcRipeMD160(senderPubKeyCompressed);
        System.out.println("BlockChain address:" + senderAddress);*//*
        return true;
    }*/



    public void transacationSendToNetwork(Transaction tx) {
        //    Bounc

    }

    public static String BytesToHex(byte[] bytes) {
        return Hex.toHexString(bytes);
    }

    private static byte[] calcSHA256(String text) throws UnsupportedEncodingException {
        byte[] bytes = getBytes(text);
        SHA256Digest digest = new SHA256Digest();
        digest.update(bytes, 0, bytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return result;

    }
/*    public static ECPublicKeyParameters toPublicKey(String privateKey) {
        BigInteger d = new BigInteger(privateKey, 16);
        ECPoint q = Domain.getG().multiply(d);
        ECPublicKeyParameters publicParams = new ECPublicKeyParameters(q, Domain);
        return publicParams;
    }*/
/*    public static boolean verifySignature(String privateKeyHex, BigInteger[] signature, byte[] msg) {
        DSAKCalculator kCalculator = new HMacDSAKCalculator(new SHA256Digest());
        ECDSASigner signer = new ECDSASigner(kCalculator);
        ECPublicKeyParameters pubKey = toPublicKey(privateKeyHex);
        signer.init(false, pubKey);
        return signer.verifySignature(msg, signature[0], signature[1]);
    }*/

//    public static boolean verifySignature(String publicKey, BigInteger[] signature, byte[] msg) {
//        DSAKCalculator kCalculator = new HMacDSAKCalculator(new SHA256Digest());
//        ECDSASigner signer = new ECDSASigner(kCalculator);
//
//        ECPublicKeyParameters pubKey = toPublicKey(privateKeyHex);
//        signer.init(false, pubKey);
//        return signer.verifySignature(msg, signature[0], signature[1]);
//    }
}

