package com.mmo.frame.utils;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * @author zhaoyuchen
 */
public abstract class AnyUtils {
    private static final Logger logger = LoggerFactory
            .getLogger(AnyUtils.class);
    /**
     * 是否处于调试开发模式
     */
    public static final boolean IS_IN_DEBUG = Boolean.parseBoolean(getConfigProperty("is_in_debug"));

    /**
     * 是否启用客户端安全检查
     */
    public static final boolean IS_IN_CLIENTSECURITY = Boolean.parseBoolean(getConfigProperty("is_in_clientsecurity"));

    public static final String CONFIG_FILE_NAME = "ntxs-config.properties";
    public static final String DEVELOP_CONFIG_FILE_NAME = "fox-develop.properties";

    public static final String PROTO_PACKAGE_NAME = "com.gyyx.wizardbone.wb_proto";


    public static final String GLOBAL_LOCK_PATH = "/lock";

    private AnyUtils() {
    }

    public static void ignoreException(Exception e) {
    }

    /**
     * 将一个集合中的元素按照顺序格式化成可显示的字符串结构,不可以在性能敏感的地方调用
     *
     * @param collection
     * @return
     */
    public static String toCollectionString(Collection<?> collection) {
        List<Object> list = new ArrayList<>(collection.size());
        for (Object object : collection) {
            list.add(object);
        }
        StringBuilder strBuilder = new StringBuilder();
        for (Object obj : list) {
            strBuilder.append(obj.toString());
            strBuilder.append(" ");
        }

        return strBuilder.toString();

    }

    /**
     * 通过一个服的id获取该服的zookeeper节点路径
     *
     * @param serverId
     * @return
     */
    public static String getSMPath(short serverId) {
        return "/" + serverId + "sm";
    }

    /**
     * 将两个int32通过位运算转换为有序拼接的int64
     *
     * @param x
     * @param y
     * @return
     */
    public static final long comTwoInt32(int x, int y) {

        long rt = ((long) x) << 32;

        rt = rt | y;

        return rt;
    }

    /**
     * 从一个int64整数中获取高(左)32位对应的整数
     *
     * @param comTwoInt32
     * @return
     */
    public static int getHighInt32(long comTwoInt32) {
        return (int) (comTwoInt32 >> (32));
    }

    /**
     * 从一个int64整数中获取低(右)32位对应的整数
     *
     * @param comTowInt32
     * @return
     */
    public static int getLowInt32(long comTowInt32) {
        return (int) comTowInt32;
    }

    /**
     * 将两个int16通过位运算转换为有序拼接的int32
     *
     * @param x
     * @param y
     * @return
     */
    public static int comTwoInt16(short x, short y) {

        int rt = ((int) x) << 16;

        rt = rt | y;

        return rt;

    }

    /**
     * 从一个int32整数中获取高(左)16位对应的整数
     *
     * @param comTwoInt16
     * @return
     */
    public static short getHighInt16(int comTwoInt16) {

        return (short) (comTwoInt16 >> (16));

    }

    /**
     * 从一个int32整数中获取低(右)16位对应的整数
     *
     * @param comTwoInt16
     * @return
     */
    public static short getLowInt16(int comTwoInt16) {

        return (short) (comTwoInt16);

    }

    /**
     * 将配置向分为运维和开发
     * 先找运维脚本，再找开发脚本
     * @param propertyName
     * @return
     */
    public static String getConfigProperty(String propertyName) {
        String value = getFileConfigProperty(CONFIG_FILE_NAME, propertyName);
        if (value == null)
            value = getFileConfigProperty(DEVELOP_CONFIG_FILE_NAME, propertyName);
        return value;
    }

    /**
     * 如果找不到，则使用默认值
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public static String getConfigProperty(String propertyName, String defaultValue) {
        String value = getConfigProperty(propertyName);
        if (value == null)
            value = defaultValue;
        return value;
    }

    /**
     * 从文件配置读取
     * @param propertyName
     * @return
     */
    private static String getFileConfigProperty(String filename, String propertyName) {
        File configFile = new File("./config/" + filename);
        if (!configFile.exists()) {
            configFile = new File("../config/" + filename);
            if (!configFile.exists()) {
                String userPath = System.getProperty("user.home");
                String sys_config_path = userPath + File.separator + filename;
                configFile = new File(sys_config_path);

                if (!configFile.exists()) {
                    logger.error("找不到配置文件 config/{}", filename);
                    CloseUtils.exit("Utils", "找不到配置文件 config/{}" + filename, 1);
                }
            }
        }

        Properties p = new Properties();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(configFile);
            p.load(fileInputStream);

            return p.getProperty(propertyName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }

    public static <E> ArrayList<E> arrange(ArrayList<E> arrayList) {

        ArrayList<E> temp = new ArrayList<E>(arrayList.size());

        for (E t : arrayList) {

            temp.add(t);

        }

        return temp;

    }

    public static IntArrayList arrange(IntArrayList list) {

        IntArrayList temp = new IntArrayList(list.size());

        for (int ix = 0, len = list.size(); ix < len; ++ix) {

            temp.add(list.getInt(ix));

        }

        return temp;

    }

    public static List<File> recurseDirs(File startDir, String regex) {

    	assert startDir != null;
    	
        List<File> fileList = new ArrayList<File>(50);

        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                fileList.addAll(recurseDirs(item, regex));
            }
            if (item.getName().matches(regex)) {
                fileList.add(item);
            }
        }
        return fileList;
    }

    /**
     * 安静的调用Closeable实现对象的close方法,确保不向外抛出异常
     *
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception ta) {
            logger.error("", ta);
        }
    }

    /**
     * 安静的关闭一个nettyChannel
     *
     * @param nettyChannel
     */
    public static ChannelFuture closeQuietly(Channel nettyChannel) {
        if (null == nettyChannel) {
            return null;
        }
        try {
            return nettyChannel.close();
        } catch (Exception ta) {
            logger.error("", ta);
        }
        return null;
    }

    public static <E> void clearCollection(ArrayList<E> a){
        if(a.isEmpty()){
            return;
        }
        a.clear();
    }

    /**
     * 重新加载log4j2的配置文件
     */
    public static void reloadLog4j2 () {
        String filename = "log4j2.xml";
        File configFile = new File("./config/" + filename);
        if (!configFile.exists()) {
            configFile = new File("../config/" + filename);
            if (!configFile.exists()) {
                String userPath = System.getProperty("user.home");
                String sys_config_path = userPath + File.separator + filename;
                configFile = new File(sys_config_path);

                if (!configFile.exists()) {
                    logger.error("找不到配置文件 config/{}", filename);
                    CloseUtils.exit("Utils", "找不到配置文件 config/{}" + filename, 1);
                }
            }
        }
        try {
            LoggerContext context =(LoggerContext) LogManager.getContext(false);
            context.setConfigLocation(configFile.toURI());
            //重新初始化Log4j2的配置上下文
            context.reconfigure();
            logger.info("=========ReloadLog4j2执行完毕=============" + LogManager.getRootLogger().getLevel());
        } catch (RuntimeException e) {
            logger.error("重载Log4j2.xml文件出现异常", e);
        }
    }

}
