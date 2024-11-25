package com.cjp.db.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author chenjianpeng
 */
public class Md5Util {

    /**
     * 用于将字节转换成16进制表示的字符数组
     */
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    private static final int TOTAL_LENGTH = 48;
    private static final int MD5_LENGTH = 32;
    private static final int SALT_LENGTH = 16;

    /**
     * 生成含有随机盐的密码
     */
    public static String generateMd5(String password) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder(SALT_LENGTH);

        // 生成一个16位的随机数
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();

        // 如果不满16位，则在开头补0
        if (len < SALT_LENGTH) {
            sb.append("0".repeat(Math.max(0, SALT_LENGTH - len)));
        }
        String salt = sb.toString();

        // 将密码和盐值拼接在一起进行md5加密
        password = md5Hex(password + salt);
        char[] cs = new char[TOTAL_LENGTH];

        // 将 %3 == 0 的位放盐值， %3 == 1 | 2 的位置放md5值
        for (int i = 0; i < TOTAL_LENGTH; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * 校验密码是否正确
     */
    public static boolean verify(String password, String md5) {
        // 存放md5
        char[] cs1 = new char[MD5_LENGTH];
        // 存放盐值
        char[] cs2 = new char[16];

        // 拆分盐值和md5值
        for (int i = 0; i < TOTAL_LENGTH; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }

        // 保存盐值
        String salt = new String(cs2);

        return md5Hex(password + salt).equals(new String(cs1));
    }

    /**
     * 对字符串进行MD5加密
     *
     * @param input 输入的字符串
     * @return MD5加密后的16进制字符串
     */
    private static String md5Hex(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5算法不存在", e);
        }
    }

    /**
     * 将字节数组转换为16进制字符串
     *
     * @param bytes 输入的字节数组
     * @return 16进制表示的字符串
     */
    private static String bytesToHex(byte[] bytes) {
        char[] result = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) {
            result[index++] = HEX_DIGITS[(b >> 4) & 0xF];
            result[index++] = HEX_DIGITS[b & 0xF];
        }
        return new String(result);
    }
}
