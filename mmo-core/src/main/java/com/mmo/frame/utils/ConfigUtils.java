package com.mmo.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaoyuchen on 2016/8/22. 
 */
public class ConfigUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    public static int getIntArg(String name) throws NoSuchConfigEntryException {
        String propertyValue = AnyUtils.getConfigProperty(name);
        if (propertyValue != null) {
            try {
                return Integer.parseInt(propertyValue);
            } catch (NumberFormatException e) {
                logger.error("配置文件属性 {} 不是整型数字", name, e);
                // 因为进程会在运行时获取一些属性，所以不能直接退出进程
                // CloseUtils.exit("Utils", "配置文件属性 {} 不是布尔类型", 1);
            }
        }

        throw new NoSuchConfigEntryException(name);
    }

    public static int checkIntArg(String name, int defaultValue) {
        try {
            return getIntArg(name);
        } catch (ConfigUtils.NoSuchConfigEntryException e) {
            logger.warn("配置文件中找不到 {} 配置项，使用默认值 {} 替代", name, defaultValue, e);
            return defaultValue;
        }

    }

    public static int getIntArg(String name, int defaultValue) {
        try {
            return getIntArg(name);
        } catch (NoSuchConfigEntryException e) {
            logger.trace("", e);
            return defaultValue;
        }
    }

    public static String getStringArg(String name) throws NoSuchConfigEntryException {
        String propertyValue = AnyUtils.getConfigProperty(name);
        if (propertyValue != null) {
            return propertyValue;
        }

        throw new NoSuchConfigEntryException(name);

    }

    public static String getStringArg(String name, String defaultValue) {
        try {
            return getStringArg(name);
        } catch (NoSuchConfigEntryException e) {
            logger.trace("", e);
            return defaultValue;
        }
    }

    public static boolean getBooleanArg(String name) throws NoSuchConfigEntryException {
        String propertyValue = AnyUtils.getConfigProperty(name);
        if (propertyValue != null) {
            try {
                return Boolean.parseBoolean(propertyValue);
            } catch (NumberFormatException e) {
                logger.error("配置文件属性 {} 不是布尔类型");
                // 因为进程会在运行时获取一些属性，所以不能直接退出进程
                // CloseUtils.exit("Utils", "配置文件属性 {} 不是布尔类型", 1);
            }
        }

        throw new NoSuchConfigEntryException(name);
    }

    public static boolean getBooleanArg(String name, boolean defaultValue) {
        try {
            return getBooleanArg(name);
        } catch (NoSuchConfigEntryException e) {
            logger.trace("", e);
            return defaultValue;
        }
    }

    public static class NoSuchConfigEntryException extends Exception {
        public NoSuchConfigEntryException(String message) {
            super(message);
        }
    }
}
