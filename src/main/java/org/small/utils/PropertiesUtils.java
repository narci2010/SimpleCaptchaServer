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

    static {
        Properties prop = new Properties();
        InputStream in = PropertiesUtils.class.getResourceAsStream("/app.properties");
        try {
            prop.load(in);
            APP_PORT = Integer.parseInt(prop.getProperty("appPort").trim());
        } catch (IOException e) {
        }
    }

    public static int getAPP_PORT() {
        return APP_PORT;
    }

    public static void main(String args[]) {
        System.out.println(getAPP_PORT());
    }
}