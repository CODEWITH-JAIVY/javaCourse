package com.supplier;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.function.Supplier;

public class Supplierinterface {

    public static String generateTOTP(String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        long timeStep = System.currentTimeMillis() / 1000 / 30;

        byte Base32String = 0;
        byte[] key = new byte[]{Base32String};
        byte[] data = ByteBuffer.allocate(8).putLong(timeStep).array();

        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);

        int offset = hash[hash.length - 1] & 0xF;
        int binary =
                ((hash[offset] & 0x7f) << 24) |
                        ((hash[offset + 1] & 0xff) << 16) |
                        ((hash[offset + 2] & 0xff) << 8) |
                        (hash[offset + 3] & 0xff);

        int otp = binary % 1000000;
        return String.format("%06d", otp);
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        SecureRandom secureRandom = new SecureRandom();

        Supplier<String> secureOtpSupplier = () -> {
            int otp = 100000 + secureRandom.nextInt(900000);
            return String.valueOf(otp);
        };

        System.out.println(secureOtpSupplier.get());
        //   System.out.println(generateTOTP("fsakdhfasfhjlksahdfkljas"));

        Random random = new Random();
        Supplier<String> supplier = () -> {
            int otp = 100000 + random.nextInt(900000);
            return String.valueOf(otp);
        };

//        System.out.println(supplier.get());

//        Random random = new Random();
//        Supplier<Integer> randam = () -> random.nextInt(100);

//        System.out.println(randam.get());

//        Supplier<String> supplier = () -> " hii sanjeet ";
//        System.out.println(supplier.get());
    }
}