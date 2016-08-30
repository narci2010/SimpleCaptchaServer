package org.small.utils;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.Date;

/**
 * LogUtilFormat
 * 打印错误日志工具类
 *
 * @author jiang haiguo
 * @date 2016/8/30
 */
public class LogUtilFormat {

    public static String getFormatMessage(String appendString, Exception exception) {
        String dateTime = TimeUtils.formatDateTime2(new Date());
        return dateTime + " " + appendString + " detail error:" + ExceptionUtils.getFullStackTrace(exception);
    }
}