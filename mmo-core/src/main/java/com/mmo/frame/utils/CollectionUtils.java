package com.mmo.frame.utils;

import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;

/**
 * Created by zhaoyuchen on 2016/8/31.
 */
public class CollectionUtils {

    public static String dumpIntList(IntList intList) {
        StringBuilder builder = new StringBuilder();
        IntListIterator iterator = intList.iterator();

        while (iterator.hasNext()) {
            int i = iterator.nextInt();
            builder.append(i);
            if (iterator.hasNext()) {
                builder.append(",");
            }
        }

        return builder.toString();
    }
}
