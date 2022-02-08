package com.stefanini.taskmanager.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();

        ClassLoader loader = PropertiesLoader.class.getClassLoader();

        try {
            InputStream inputStream = loader.getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new FileNotFoundException();
            }
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}