package org.small.utils;

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
}