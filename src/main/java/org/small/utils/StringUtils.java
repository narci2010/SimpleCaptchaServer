package org.small.utils;

/**
 * StringUtils
 *
 * @author jiang haiguo
 * @date 2016/7/29
 */
public class StringUtils {

    /**
     * 汉字转unicode
     *
     * @param string
     * @return
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * 判断是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return org.apache.commons.lang3.StringUtils.isEmpty(string);
    }

    /**
     * 判断是否为非空
     *
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}