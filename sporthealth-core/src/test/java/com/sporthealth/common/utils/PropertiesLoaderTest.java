package com.sporthealth.common.utils;

import org.junit.Test;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-06-15 20:25
 **/
public class PropertiesLoaderTest {

    @Test
    public void getProperty() {

        PropertiesLoader propertiesLoader = new PropertiesLoader("/sporthealth.properties");
        System.out.println(propertiesLoader.getProperty("app.version"));
        System.out.println(propertiesLoader.getProperty("app.version2", "1.0"));
        System.out.println(propertiesLoader.getProperty("app.version3"));
    }

    @Test
    public void getPropertyByClass() {

        PropertiesLoader propertiesLoader = new PropertiesLoader();
        propertiesLoader.loadPropertiesByClass(new String[]{"/sporthealth.properties"});
        System.out.println(propertiesLoader.getProperty("app.version"));
        System.out.println(propertiesLoader.getProperty("app.version2", "1.0"));
        System.out.println(propertiesLoader.getProperty("app.version3"));
    }
}