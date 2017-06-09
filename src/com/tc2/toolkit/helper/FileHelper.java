package com.tc2.toolkit.helper;

import com.alibaba.fastjson.JSON;
import com.tc2.toolkit.print.Console;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHelper {
    public static byte[] read(String filePath) {
        InputStream in = null;
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
}
