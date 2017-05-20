package com.tc2.toolkit.time;

import java.util.Date;

public class Time {
    public static long now() {
        return new Date().getTime();
    }
}
