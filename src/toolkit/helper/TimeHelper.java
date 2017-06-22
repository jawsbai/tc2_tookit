package toolkit.helper;

import java.util.Date;

public class TimeHelper {
    public static Date now() {
        return new Date();
    }

    public static long nowTime() {
        return new Date().getTime();
    }
}
