package org.small.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtils
 *
 * @author jiang haiguo
 * @date 2016/8/10
 */
public class TimeUtils {

    /**
     * 获取不一样的毫秒数
     *
     * @return
     */
    public static synchronized long getDifTime() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        } finally {
            return System.currentTimeMillis();
        }
    }

    public static String formatDateTime2(Date date) {
        SimpleDateFormat sFormat = new SimpleDateFormat();
        sFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sFormat.format(date);
    }
}