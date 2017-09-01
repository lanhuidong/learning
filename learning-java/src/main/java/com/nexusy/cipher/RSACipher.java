package com.nexusy.cipher;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author lan
 * @since 2017-09-01
 */
public class RSACipher {

    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public static void main(String[] args) throws Exception {
        int keyLength = 2048;
        generateKey(keyLength);
        byte[] encryptData = privateKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
        System.out.println("RSA private key encrypt: \n" + Base64.getEncoder().encodeToString(encryptData));
        System.out.println("RSA public key decrypt:\n" + publicKeyDecrypt(encryptData));
        System.out.println();

        encryptData = publicKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
        System.out.println("RSA public key encrypt:\n" + Base64.getEncoder().encodeToString(encryptData));
        System.out.println("RSA public key decrypt:\n" + privateKeyDecrypt(encryptData));
    }

    public static byte[] privateKeyEncrypt(byte[] plainData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(plainData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String publicKeyDecrypt(byte[] encryptData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(cipher.doFinal(encryptData), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] publicKeyEncrypt(byte[] plainData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(plainData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String privateKeyDecrypt(byte[] encryptData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(encryptData), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void generateKey(int length) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(length);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
