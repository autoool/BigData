package utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author sam
 * 2019/3/27
 */
public class PropertiesUtil {

    public static Properties properties = null;

    static {
        InputStream is = ClassLoader.getSystemResourceAsStream("hbase_consumer.properties");
        properties = new Properties();
        try {
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
