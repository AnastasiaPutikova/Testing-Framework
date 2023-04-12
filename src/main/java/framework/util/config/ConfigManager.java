package framework.util.config;

import framework.util.logger.LoggerManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties;

    static {
        String propertyFilePath = "src/main/java/framework/util/config/config.properties";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                LoggerManager.logError(e.toString());
            }
        } catch (FileNotFoundException e) {
            LoggerManager.logError(e.toString());
            try {
                throw new RuntimeException("config.properties not found at " + propertyFilePath);
            } catch (RuntimeException runtimeException){
                LoggerManager.logError(runtimeException.toString());
            }
        }
    }

    public static int getIntValueOf(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        if (propertyValue != null) return Integer.parseInt(propertyValue);
        else try {
            throw new RuntimeException(propertyName+ " is not specified in the config.properties file");
        }catch (RuntimeException e){
            LoggerManager.logError(e.toString());
        }
        assert false;
        return Integer.parseInt(propertyValue);
    }

    public static String getStringValueOf(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        try {
            if (propertyValue != null)
                return propertyValue;
            else throw new RuntimeException(propertyName + " is not specified in the config.properties file");
        }catch (RuntimeException e){
            LoggerManager.logError(e.toString());
        }
        assert false;
        return propertyValue;
    }
}
