package com.mmo.frame.utils;

import com.gyyx.wizardbone.template.fox.ExcelTemplateHelper;
import com.gyyx.wizardbone.template.fox.LangText;
import com.mmo.frame.template.fox.ExcelTemplateHelper;
import com.mmo.frame.template.fox.LangText;

/**
 * Created by liwenkai on 2016/8/19.
 */
public class StringUtils {

    /**
     * 相当于String.replace方法
     * 将字符串中的某个字符替换成要替换的字符
     * @param string
     * @param sign
     * @param replace
     * @return
     */
    public static String replace (String string, String sign, String replace) {
        if (sign == null || sign.length() == 0)
            return string;

        StringBuilder sb = new StringBuilder(string);
        int length = sign.length();
        int start = sb.indexOf(sign);
        int end = length + start;
        if (start <= -1) {
            return string;
        }
        return sb.replace(start, end, replace).toString();
    }

    /**
     * 把string中的所有sign都替换为replace
     * @param string
     * @param sign
     * @param replace
     * @return
     */
    public static String replaceAll(String string, String sign, String replace, int startParam) {
        if (sign == null || sign.length() == 0)
            return string;

        int start = startParam;
        StringBuilder sb = new StringBuilder(string);
        int length = sign.length();
        while ((start = sb.indexOf(sign, start)) >= 0) {
            sb.replace(start, length + start, replace);
            start += replace.length();
        }

        return sb.toString();
    }

    //用实际内容填充含有占位符的字符串
    public static String fillSeat(String sourceParam, String sign,  String... elements) {
        if (sign == null || sign.length() == 0)
            return sourceParam;

        StringBuilder sb = new StringBuilder(sourceParam);
        int length = sign.length();
        for (String str : elements) {
            int start = sb.indexOf(sign);
            int end = length + start;
            if (start <= -1) {
                break;
            }
            sb.replace(start, end, str);
        }
        return sb.toString();
    }

    //用实际内容填充含有占位符的字符串
    public static String fillSeatByTemplateId(int id, String sign,
                                              String... elements) {

        LangText template = ExcelTemplateHelper.getLangText().getTemplate(id);

        return fillSeat(template.text, sign, elements);
    }

    //字符串数量转成int数组
    public static int[] convertStringArrayToIntArray(String[] arrs) {
        int[] intArrs = new int[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            intArrs[i] = Integer.parseInt(arrs[i]);
        }
        return intArrs;
    }

    /** 判断字符串是否是数字类型
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        for(int i = str.length() - 1; i>=0; --i){
            if(0 == i && str.charAt(i)=='-' && str.length()>1){
            }else{
                if(!Character.isDigit(str.charAt(i))){
                    return false;
                }
            }

        }
        return true;
    }
}
