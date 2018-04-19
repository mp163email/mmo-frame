package com.mmo.frame.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;

/**
 * Created by 李忠波 on 2016/10/27 0027.
 *
 * 退出时一些处理，帮助发现问题
 */
public final class CloseUtils {
    private static final Logger logger = LoggerFactory.getLogger(CloseUtils.class);

    /**
     * 退出函数
     */
    public static void exit(String moduleName, String name, int status) {

        /// 等待几秒，保证日志刷到硬盘
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            File file = new File("exit_" + moduleName + ".log");

            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(System.currentTimeMillis());
            String time = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
                    + now.get(Calendar.DAY_OF_MONTH) + " " + now.get(Calendar.HOUR_OF_DAY) +
                    ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);

            FileWriter fw = new FileWriter(file, true);
            fw.write(time + " " + name + " exit code:" + status + "\r\n");

            fw.close();
        }
        catch (Exception e) {
            // 此处不需要处理
            logger.debug("", e);
        }

        System.exit(status);
    }

}
