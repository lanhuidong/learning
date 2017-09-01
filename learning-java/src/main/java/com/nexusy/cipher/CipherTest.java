package com.nexusy.cipher;

import javax.crypto.SecretKey;

/**
 * @author lan
 * @since 2017-09-01
 */
public class CipherTest {

    public static final String PLAIN_TEXT = "{\"action\":105,\"type\":1,\"request\":{\"deviceSn\":\"XX1000\","
            + "\"channelNo\":0,\"alarmTime\":1503472276881,\"alarmType\":1,\"imageUrl\":\"http://www.manniu.com/1.jpg\""
            + ",\"videoUrl\":\"http://www.manniu.com/1.mp4\",\"vStartTime\":1503472276881,\"vEndTime\":1503472276881}}";

    public static final String RSA_TEXT = "RlA8aCPlsuATT227kKTg003ncP35HYRI";

    public static final byte[] IVPARAMETERS_8 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
    public static final byte[] IVPARAMETERS_16 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

    public static void main(String[] args) throws Exception {
        int total = 100000;

        warmupAES(IVPARAMETERS_16);
        testAES(total, 128, IVPARAMETERS_16);
        testAES(total, 192, IVPARAMETERS_16);
        testAES(total, 256, IVPARAMETERS_16);

        warmupDES(IVPARAMETERS_8);
        testDES(total, 56, IVPARAMETERS_8);

        warmup3DES(IVPARAMETERS_8);
        test3DES(total, 112, IVPARAMETERS_8);
        test3DES(total, 168, IVPARAMETERS_8);

        warmupRSA();
        testRSA(total, 512);
        testRSA(total, 1024);
        testRSA(total, 2048);
    }

    private static void warmupAES(byte[] iv) throws Exception {
        byte[] secretBytes = AESCipher.generateAESSecretKey(128);
        SecretKey key = AESCipher.restoreSecretKey(secretBytes);
        for (int i = 0; i < 1000000; i++) {
            byte[] data = AESCipher.aesEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
            AESCipher.aesEcbDecrypt(data, key);
            data = AESCipher.aesCbcEncrypt(PLAIN_TEXT.getBytes("UTF-8"), key, iv);
            AESCipher.aesCbcDecrypt(data, key, iv);
        }
    }

    private static void warmupDES(byte[] iv) throws Exception {
        byte[] secretBytes = DESCipher.generateAESSecretKey(56);
        SecretKey key = DESCipher.restoreSecretKey(secretBytes);
        for (int i = 0; i < 1000000; i++) {
            byte[] data = DESCipher.desEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
            DESCipher.desEcbDecrypt(data, key);
            data = DESCipher.desCbcEncrypt(PLAIN_TEXT.getBytes("UTF-8"), key, iv);
            DESCipher.desCbcDecrypt(data, key, iv);
        }
    }

    private static void warmupRSA() throws Exception {
        int keyLength = 512;
        RSACipher.generateKey(keyLength);
        for (int i = 0; i < 1000000; i++) {
            byte[] encryptData = RSACipher.privateKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
            RSACipher.publicKeyDecrypt(encryptData);
            encryptData = RSACipher.publicKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
            RSACipher.privateKeyDecrypt(encryptData);
        }
    }

    private static void warmup3DES(byte[] iv) throws Exception {
        byte[] secretBytes = DESedeCipher.generateAESSecretKey(112);
        SecretKey key = DESedeCipher.restoreSecretKey(secretBytes);
        for (int i = 0; i < 1000000; i++) {
            byte[] data = DESedeCipher.desEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
            DESedeCipher.desEcbDecrypt(data, key);
            data = DESedeCipher.desCbcEncrypt(PLAIN_TEXT.getBytes("UTF-8"), key, iv);
            DESedeCipher.desCbcDecrypt(data, key, iv);
        }
    }

    private static void testAES(int total, int bitNum, byte[] iv) throws Exception {
        byte[] secretBytes = AESCipher.generateAESSecretKey(bitNum);
        SecretKey key = AESCipher.restoreSecretKey(secretBytes);

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            AESCipher.aesEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("AES ECB encrypt " + total + " times cost " + (t2 - t1) + "ms, key length " + bitNum);
        byte[] encodedData = AESCipher.aesEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            AESCipher.aesEcbDecrypt(encodedData, key);
        }
        long t4 = System.currentTimeMillis();
        System.out.println("AES ECB decrypt " + total + " times cost " + (t4 - t3) + "ms, key length " + bitNum);

        long t5 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            AESCipher.aesCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, iv);
        }
        long t6 = System.currentTimeMillis();
        System.out.println("AES CBC encrypt " + total + " times cost " + (t6 - t5) + "ms, key length " + bitNum);
        encodedData = AESCipher.aesCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, iv);
        long t7 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            AESCipher.aesCbcDecrypt(encodedData, key, iv);
        }
        long t8 = System.currentTimeMillis();
        System.out.println("AES CBC decrypt " + total + " times cost " + (t8 - t7) + "ms, key length " + bitNum);
    }

    private static void testDES(int total, int bitNum, byte[] iv) throws Exception {
        byte[] secretBytes = DESCipher.generateAESSecretKey(bitNum);
        SecretKey key = DESCipher.restoreSecretKey(secretBytes);

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESCipher.desEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("DES ECB encrypt " + total + " times cost " + (t2 - t1) + "ms, key length " + bitNum);
        byte[] encodedData = DESCipher.desEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESCipher.desEcbDecrypt(encodedData, key);
        }
        long t4 = System.currentTimeMillis();
        System.out.println("DES ECB decrypt " + total + " times cost " + (t4 - t3) + "ms, key length " + bitNum);

        long t5 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESCipher.desCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, iv);
        }
        long t6 = System.currentTimeMillis();
        System.out.println("DES CBC encrypt " + total + " times cost " + (t6 - t5) + "ms, key length " + bitNum);
        encodedData = DESCipher.desCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, iv);
        long t7 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESCipher.desCbcDecrypt(encodedData, key, iv);
        }
        long t8 = System.currentTimeMillis();
        System.out.println("DES CBC decrypt " + total + " times cost " + (t8 - t7) + "ms, key length " + bitNum);
    }

    private static void test3DES(int total, int bitNum, byte[] iv) throws Exception {
        byte[] secretBytes = DESedeCipher.generateAESSecretKey(bitNum);
        SecretKey key = DESedeCipher.restoreSecretKey(secretBytes);

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESedeCipher.desEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
        }
        long t2 = System.currentTimeMillis();
        System.out.println("3DES ECB encrypt " + total + " times cost " + (t2 - t1) + "ms, key length " + bitNum);
        byte[] encodedData = DESedeCipher.desEcbEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key);
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESedeCipher.desEcbDecrypt(encodedData, key);
        }
        long t4 = System.currentTimeMillis();
        System.out.println("3DES ECB decrypt " + total + " times cost " + (t4 - t3) + "ms, key length " + bitNum);

        long t5 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESedeCipher.desCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, iv);
        }
        long t6 = System.currentTimeMillis();
        System.out.println("3DES CBC encrypt " + total + " times cost " + (t6 - t5) + "ms, key length " + bitNum);
        encodedData = DESedeCipher.desCbcEncrypt(CipherTest.PLAIN_TEXT.getBytes("UTF-8"), key, iv);
        long t7 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            DESedeCipher.desCbcDecrypt(encodedData, key, iv);
        }
        long t8 = System.currentTimeMillis();
        System.out.println("3DES CBC decrypt " + total + " times cost " + (t8 - t7) + "ms, key length " + bitNum);
    }

    private static void testRSA(int total, int bitNum) throws Exception {
        RSACipher.generateKey(bitNum);

        long t1 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            RSACipher.privateKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
        }
        long t2 = System.currentTimeMillis();
        System.out.println("RSA private key encrypt " + total + " times cost " + (t2 - t1) + "ms, key length " + bitNum);
        byte[] encodedData = RSACipher.privateKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            RSACipher.publicKeyDecrypt(encodedData);
        }
        long t4 = System.currentTimeMillis();
        System.out.println("RSA public key decrypt " + total + " times cost " + (t4 - t3) + "ms, key length " + bitNum);

        long t5 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            RSACipher.publicKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
        }
        long t6 = System.currentTimeMillis();
        System.out.println("RSA public key encrypt " + total + " times cost " + (t6 - t5) + "ms, key length " + bitNum);
        encodedData = RSACipher.publicKeyEncrypt(CipherTest.RSA_TEXT.getBytes("UTF-8"));
        long t7 = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            RSACipher.privateKeyDecrypt(encodedData);
        }
        long t8 = System.currentTimeMillis();
        System.out.println("RSA private key decrypt " + total + " times cost " + (t8 - t7) + "ms, key length " + bitNum);
    }

}
