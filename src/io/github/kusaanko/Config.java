package io.github.kusaanko;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static Properties config = new Properties();
    private static Path configPath = Paths.get("mcaddtojar.cfg");

    public static void load() {
        if(Files.exists(configPath)) {
            try {
                config.load(Files.newBufferedReader(configPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        try {
            config.store(Files.newBufferedWriter(configPath), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key, String init) {
        String value = null;
        if((value = config.getProperty(key))!=null) {
            return value;
        }
        config.setProperty(key, init);
        return init;
    }

    public static void put(String key, String value) {
        config.setProperty(key, value);
    }
}
