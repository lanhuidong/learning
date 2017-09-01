package com.nexusy.cipher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author lan
 * @since 2017-09-01
 */
public class DESCipher {

    public static final String KEY_ALGORITHM = "DES";
    public static final String ECB_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    public static final String CBC_CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        byte[] secretBytes = generateAESSecretKey(56);
        SecretKey key = restoreSecretKey(secretBytes);
        byte[] encodedText = desEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);

        System.out.println("DES ECB encrypt with Base64: \n" + Base64.getEncoder().encodeToString(encodedText));
        System.out.println("DES ECB decrypt: \n" + desEcbDecrypt(encodedText, key));


        encodedText = desCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, CipherTest.IVPARAMETERS_8);


        System.out.println("DES CBC encrypt with Base64: \n" + Base64.getEncoder().encodeToString(encodedText));
        System.out.println("DES CBC decrypt: \n" + desCbcDecrypt(encodedText, key, CipherTest.IVPARAMETERS_8));
    }

    public static byte[] desEcbEncrypt(byte[] plainData, SecretKey securekey) {
        try {
            Cipher cipher = Cipher.getInstance(ECB_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            return cipher.doFinal(plainData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desEcbDecrypt(byte[] encryptData, SecretKey securekey) {
        try {
            Cipher cipher = Cipher.getInstance(ECB_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            return new String(cipher.doFinal(encryptData), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] desCbcEncrypt(byte[] plainData, SecretKey securekey, byte[] IVParameter) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IVParameter);
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, securekey, ivParameterSpec);
            return cipher.doFinal(plainData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desCbcDecrypt(byte[] encryptData, SecretKey securekey, byte[] IVParameter) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IVParameter);
            Cipher cipher = Cipher.getInstance(CBC_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, securekey, ivParameterSpec);
            return new String(cipher.doFinal(encryptData), "UTF-8");
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
        try {
            DESKeySpec desKey = new DESKeySpec(secretBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generateSecret(desKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
