package com.haiyangroup.library.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zj on 2015/8/16.
 */
public class PasswordSHA {

    public byte[] encode(String info,String shaType) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance(shaType);
        byte[] srcBytes = info.getBytes();
        //使用srcByte更新摘要
        sha.update(srcBytes);
        //完成哈希计算，得到result
        byte[] resultBytes = sha.digest();
        return resultBytes;
    }

    public byte[] encodeSHA1(String info) throws NoSuchAlgorithmException {
        return encode(info,"SHA1");
    }
    public byte[] encodeSHA256(String info) throws NoSuchAlgorithmException {
        return encode(info, "SHA-256");
    }
    public byte[] encodeSHA384(String info) throws NoSuchAlgorithmException {
        return encode(info, "SHA-384");
    }
    public byte[] encodeSHA512(String info) throws NoSuchAlgorithmException {
        return encode(info, "SHA-512");
    }

    public static String hexString(byte[] bytes){
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < bytes.length; i++) {
            int val = ((int) bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String msg1 = "123456";
        PasswordSHA sha1 = new PasswordSHA();
        String sha12= hexString(sha1.encodeSHA1(msg1));
        System.out.println("明文:"+msg1);
        System.out.println("密文:"+sha12);
    }


}
