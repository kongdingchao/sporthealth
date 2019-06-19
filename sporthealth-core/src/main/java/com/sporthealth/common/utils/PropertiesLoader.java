package com.sporthealth.common.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * @program: sporthealth
 * @description: 配置文件读取
 * @author: kongdingchao
 * @create: 2019-06-15 20:07
 **/
public class PropertiesLoader {
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    private Properties properties;

    /**
     * @Description: 通过resourcesPaths构造PropertiesLoader
     */
    public PropertiesLoader() {
        properties = new Properties();
    }

    /**
    * @Description: 通过resourcesPaths构造PropertiesLoader
    */
    public PropertiesLoader(String... resourcesPaths) {
        properties = loadProperties(resourcesPaths);
    }

    /**
     * 取出Property，但以System的Property优先,取不到返回空字符串.
     */
    private String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        return null;
    }

    /**
     * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
     */
    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * 取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.
     */
    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
    * @Description: 通过spring DefaultResourceLoader加载配置
    */
    private Properties loadProperties(String[] resourcesPaths) {
        Properties props = new Properties();
        for (String resourcesPath : resourcesPaths) {
            Resource resource = resourceLoader.getResource(resourcesPath);
            try {
                InputStream inputStream = resource.getInputStream();
                props.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return props;
    }

    /**
     * @Description: 通过Class.getResourceAsStream读取配置
     */
    public void loadPropertiesByClass(String[] resourcesPaths) {
        for (String resourcesPath : resourcesPaths) {
            try {
                InputStream in = this.getClass().getResourceAsStream(resourcesPath);
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
