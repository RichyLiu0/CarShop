package org.carshop.utilities;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class SettingHelper {

    public static String getProperty(String code) {
        try {
            try {
                Resource resource = new ClassPathResource("application.properties");
                Properties props = PropertiesLoaderUtils.loadProperties(resource);
                return props.getProperty(code);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } catch (Exception ex) {
            try {
                LogHelper.Log("SystemError", ex.getMessage(), Long.toString(Thread.currentThread().getId()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }
    }

}
