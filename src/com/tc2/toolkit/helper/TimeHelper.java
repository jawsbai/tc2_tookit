package com.tc2.toolkit.helper;

import java.util.Date;

public class TimeHelper {
    public static long now() {
        return new Date().getTime();
    }
}