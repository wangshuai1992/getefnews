package com.wangshuai.efnews.common.utils;

/**
 * 中文判断工具
 *
 * @author wangshuai
 * @version V1.0
 * @date 2018-01-01 19:40
 */
public class CheckChineseUtil {

    /**
     * GENERAL_PUNCTUATION 判断中文的“号
     * CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
     * HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean containsChinese(String str) {
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 弃用，通过区间判断不精确
     *
     * @param chineseStr
     * @return
     */
    @Deprecated
    public static boolean containsChinese_f1(String chineseStr) {
        char[] charArray = chineseStr.toCharArray();
        for (char aCharArray : charArray) {
            if ((aCharArray >= 0x4e00) && (aCharArray <= 0x9fbb)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 弃用。和方法containsChinese比效率太低。
     */
    @Deprecated
    public static boolean containsChinese_f2() {
        String str = "！？";
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + 1).matches("[\\u4e00-\\u9fbb]+")) {
                return true;
            }
        }
        return false;
    }
}
