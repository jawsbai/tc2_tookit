package com.tc2.toolkit.utils;

public class AutoCloseableHelper {
    public static void close(AutoCloseable closeable) {
        if(closeable!=null){
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
