package com.tc2.toolkit.utils;

import java.io.UnsupportedEncodingException;

public class StringHelper {
    public static byte[] toBytes(String str) {
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static String fromBytes(byte[] bytes) {
        try {
            return new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String fromBytes(byte[] bytes, int off, int len) {
        try {
            return new String(bytes, off, len, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
