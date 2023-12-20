package io.plagov.testautomationexample.api.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static String getApiAccessToken()  {
        var propertiesFile = Thread.currentThread().getContextClassLoader().getResource("test.properties");
        if (propertiesFile == null) {
            throw new RuntimeException("Properties file cannot be loaded from resources");
        }
        var properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFile.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (String) properties.get("apiAccessToken");
    }
}
