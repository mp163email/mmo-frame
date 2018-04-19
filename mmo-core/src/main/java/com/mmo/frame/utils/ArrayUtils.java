package com.mmo.frame.utils;

import java.util.Arrays;

/**
 * Created by zhaoyuchen on 2016/12/17. 
 */
public class ArrayUtils {

    /**
     * 字符串数组转int数组
     * @param strArrays
     * @return
     */
    public static int [] stringToInt (String [] strArrays) {
        int[] intArrays = new int[strArrays.length];
        for (int i = 0; i < strArrays.length; i++) {
            intArrays [i] = Integer.valueOf(strArrays[i]);
        }
        return intArrays;
    }

    public static String dumpArray(int[] array) {
        if (array.length == 0) {
            return "int[]{}";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("int[]{");

        for (int i = 0; i < array.length; ++i) {
            builder.append(array[i]);
            if (i < array.length - 1) {
                builder.append(",");
            }
        }

        return builder.toString();
    }

    public static String dumpArray(String[] array) {
        if (array.length == 0) {
            return "int[]{}";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("int[]{");

        for (int i = 0; i < array.length; ++i) {
            builder.append(array[i]);
            if (i < array.length - 1) {
                builder.append(",");
            }
        }

        builder.append("}");

        return builder.toString();
    }

    public static int sumArray(int[] array) {
        int sum = 0;
        for(int num : array){
            sum += num;
        }
        return sum;
    }

    /**
     * 数组查找
     * @param array
     * @param value
     * @return >= 0, 在数组中的位置，-1没有找到
     */
    public static <T> int indexOf(T[] array, T value) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == value)
                return i;
        }

        return -1;
    }

    /**
     * 数组中是否包含指定的值
     * @param array
     * @param value
     * @return
     */
    public static <T> boolean contains(T[] array, T value) {
        return indexOf(array, value) >= 0;
    }

    /**
     * 数组查找
     * @param array
     * @param value
     * @return >= 0, 在数组中的位置，-1没有找到
     */
    public static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == value)
                return i;
        }

        return -1;
    }

    /**
     * 数组中是否包含指定的值
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(int[] array, int value) {
        return indexOf(array, value) >= 0;
    }

    /**
     * 数组查找
     * @param array
     * @param value
     * @return >= 0, 在数组中的位置，-1没有找到
     */
    public static int indexOf(byte[] array, byte value) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == value)
                return i;
        }

        return -1;
    }

    /**
     * 数组中是否包含指定的值
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(byte[] array, byte value) {
        return indexOf(array, value) >= 0;
    }

    /**
     * 数组查找
     * @param array
     * @param value
     * @return >= 0, 在数组中的位置，-1没有找到
     */
    public static int indexOf(short[] array, short value) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == value)
                return i;
        }

        return -1;
    }

    /**
     * 数组中是否包含指定的值
     * @param array
     * @param value
     * @return
     */
    public static boolean contains(short[] array, short value) {
        return indexOf(array, value) >= 0;
    }

    /**
     * 向一个满数组添加一个元素
     * @param arrayParam
     * @param t
     * @param <T>
     */
    public static <T> T[] addToArray(T[] arrayParam, T t) {
        T[] array = Arrays.copyOf(arrayParam, arrayParam.length + 1);
        array[array.length - 1] = t;
        return array;
    }
}
