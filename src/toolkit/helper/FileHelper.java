package toolkit.helper;

import com.alibaba.fastjson.JSON;

import java.io.*;

public class FileHelper {
    public static byte[] read(String filePath) {
        FileInputStream in = null;
        try {
            File file = new File(filePath);
            in = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes, 0, bytes.length);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            AutoCloseableHelper.close(in);
        }
        return null;
    }

    public static String readString(String filePath) {
        return StringHelper.fromBytes(read(filePath));
    }

    public static <T> T readJson(String filePath, Class<T> c) {
        try {
            String str = FileHelper.readString(filePath);
            return JSON.parseObject(str, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean write(String filePath, byte[] data) {
        FileOutputStream out = null;
        try {
            File file = new File(filePath);
            out = new FileOutputStream(file);
            out.write(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            AutoCloseableHelper.close(out);
        }
        return false;
    }

    public static boolean writeString(String filePath, String content) {
        return write(filePath, StringHelper.toBytes(content));
    }
}
