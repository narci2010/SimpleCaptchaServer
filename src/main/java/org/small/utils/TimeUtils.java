package org.small.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.misc.BASE64Encoder;

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

    public static void main(String[] args) {
        String jiang = "jaijsidjfaisjf\\/]as..;";
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println(encoder.encode(jiang.getBytes()));
        ObjectMapper mapperObj = new ObjectMapper();
        String returnMsg = "";
        try {
            returnMsg = mapperObj.writeValueAsString(jiang.getBytes());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(returnMsg);
    }
}