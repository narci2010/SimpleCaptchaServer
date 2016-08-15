package org.small.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtils 读取配置文件
 *
 * @author jiang haiguo
 * @date 2016/8/12
 */
public class PropertiesUtils {

    /**
     * 监听的端口号
     */
    private static int APP_PORT;

    /**
     * 验证码图片长度
     */
    private static int WIDTH;

    /**
     * 验证码图片高度
     */
    private static int HEIGHT;

    /**
     * 验证码长度
     */
    private static int LEN;

    /**
     * 验证码格式（中文，英文，数字，音频）
     */
    private static String STYLE;

    /**
     * 验证码响应时输出格式
     */
    private static String OUTPUT;

    private static String CHS_CAPTCHA;

    public static String getChsCaptcha() {
        return CHS_CAPTCHA;
    }

    static {
        Properties prop = new Properties();
        InputStream in = PropertiesUtils.class.getResourceAsStream("/app.properties");
        try {
            prop.load(in);
            APP_PORT = Integer.parseInt(prop.getProperty("appPort").trim());
            WIDTH = Integer.parseInt(prop.getProperty("width").trim());
            HEIGHT = Integer.parseInt(prop.getProperty("height").trim());
            LEN = Integer.parseInt(prop.getProperty("len").trim());
            STYLE = prop.getProperty("catchaStyle").trim();
            OUTPUT = prop.getProperty("outputType").trim();
            CHS_CAPTCHA = prop.getProperty("baseChsCaptcha").trim();
        } catch (IOException e) {
        }
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getLEN() {
        return LEN;
    }

    public static String getSTYLE() {
        return STYLE;
    }

    public static String getOUTPUT() {
        return OUTPUT;
    }

    public static int getAPP_PORT() {
        return APP_PORT;
    }

    public static void main(String args[]) {
        System.out.println(getAPP_PORT());
        System.out.println(getOUTPUT());
        System.out.println(getSTYLE());
        System.out.println(getLEN());
        System.out.println(getHEIGHT());
        System.out.println(getWIDTH());
    }
}
