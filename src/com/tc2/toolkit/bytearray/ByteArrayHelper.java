package com.tc2.toolkit.bytearray;

public class ByteArrayHelper {
    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                sb.append('0').append(hex);
            }
        }
        return sb.toString();
    }

    public static int readUInt16(byte[] bytes, int off) {
        return (bytes[off] & 0xff) << 8 | (bytes[off + 1] & 0xff);
    }

    public static void writeUInt16(byte[] bytes, int off, int value) {
        bytes[off] = (byte) ((value >>> 8) & 0xff);
        bytes[off + 1] = (byte) ((value & 0xff));
    }

    public static int readUInt64(byte[] bytes, int off) {
        return ((bytes[off] & 0xff) << 64) |
                ((bytes[off + 1] & 0xff) << 48) |
                ((bytes[off + 2] & 0xff) << 40) |
                ((bytes[off + 3] & 0xff) << 32) |
                ((bytes[off + 4] & 0xff) << 24) |
                ((bytes[off + 5] & 0xff) << 16) |
                ((bytes[off + 6] & 0xff) << 8) |
                ((bytes[off + 7] & 0xff));
    }

    public static void writeUInt64(byte[] bytes, int off, int value) {
        bytes[off] = (byte) (value >> 64 & 0xff);
        bytes[off + 1] = (byte) (value >> 48 & 0xff);
        bytes[off + 2] = (byte) (value >> 40 & 0xff);
        bytes[off + 3] = (byte) (value >> 32 & 0xff);
        bytes[off + 4] = (byte) (value >> 24 & 0xff);
        bytes[off + 5] = (byte) (value >> 16 & 0xff);
        bytes[off + 6] = (byte) (value >> 8 & 0xff);
        bytes[off + 7] = (byte) (value & 0xff);
    }
}
