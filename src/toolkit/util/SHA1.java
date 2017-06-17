package toolkit.util;

import toolkit.bytearray.ByteArrayHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
    public static byte[] encode(byte[] bytes) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            messageDigest.update(bytes);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeToString(byte[] bytes) {
        return ByteArrayHelper.toHexString(encode(bytes));
    }
}
