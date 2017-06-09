package com.tc2.toolkit.helper;

public class StringBuilderHelper {
    public static void removeLast(StringBuilder sb, int count) {
        int len = sb.length();
        if (len > 0) {
            sb.delete(len - count, len);
        }
    }
}
