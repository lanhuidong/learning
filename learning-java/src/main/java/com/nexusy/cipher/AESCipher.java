package com.nexusy.cipher;

/**
 * 当秘钥长度为192或256时，需要替换JAVA_HOME/jre/lib/security下的local_policy.jar和US_export_policy.jar
 *
 * @author lanhuidong
 * @since 2017-08-31
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESCipher {

    public static final String KEY_ALGORITHM = "AES";
    public static final String ECB_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String CBC_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static final byte[] IVPARAMETERS = new byte[]{1, 2, 3, 4, 5, 6, 7,
            8, 9, 10, 11, 12, 13, 14, 15, 16};

    public static void main(String[] arg) throws Exception {
        byte[] secretBytes = generateAESSecretKey(128);
        SecretKey key = restoreSecretKey(secretBytes);
        byte[] encodedText = aesEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);

        System.out.println("AES ECB encrypt with Base64: \n" + Base64.getEncoder().encodeToString(encodedText));
        System.out.println("AES ECB decrypt: \n" + aesEcbDecrypt(encodedText, key));


        encodedText = aesCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, IVPARAMETERS);


        System.out.println("AES CBC encrypt with Base64: \n" + Base64.getEncoder().encodeToString(encodedText));
        System.out.println("AES CBC decrypt: \n" + aesCbcDecrypt(encodedText, key, IVPARAMETERS));
    }

    public static byte[] aesEcbEncrypt(byte[] plainText, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ECB_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesEcbDecrypt(byte[] decodedText, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ECB_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(decodedText), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static byte[] aesCbcEncrypt(byte[] plainText, SecretKey key, byte[] IVParameter) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IVParameter);
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            return cipher.doFinal(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesCbcDecrypt(byte[] decodedText, SecretKey key, byte[] IVParameter) {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IVParameter);
        try {
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            return new String(cipher.doFinal(decodedText), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static byte[] generateAESSecretKey(int bitNum) {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGenerator.init(bitNum);
            return keyGenerator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SecretKey restoreSecretKey(byte[] secretBytes) {
        return new SecretKeySpec(secretBytes, KEY_ALGORITHM);
    }
}
