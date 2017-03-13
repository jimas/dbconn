package com.jimas.dbconn.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5 arithmetic encoding.
 * 
 * @author weqinjia.liu
 */
public class MD5Util {

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * get Encode MD5 String
     * 
     * @param origin
     * @return MD5 String String
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        if (origin == null) {
            origin = "";
        }
        resultString = new String(origin);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5Util : " + e.getMessage());
        }
        resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        return resultString;
    }
    
}
