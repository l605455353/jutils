package top.lfyao.jutils.math;

import java.util.Random;

/**
 * 随机数工具类
 */
public class RandomUtils {
    private static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CHAR = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER_CHAR = "0123456789";


    /**
     * 获取指定长度的随机数（包含大小写、数字）
     * @param length
     * @return
     */
    public static String generateString(int length) {
        return getString(length, ALL_CHAR);
    }

    /**
     * 获取指定长度的随机数（包含大小写字母）
     * @param length
     * @return
     */
    public static String generateMixString(int length) {
       return getString(length, LETTER_CHAR);
    }

    /**
     * 获取指定长度的随机数（只包含小写字母）
     * @param length
     * @return
     */
    public static String generateLowerString(int length) {
        return getString(length, LOWER_CHAR);
    }

    /**
     * 获取指定长度的随机数（只包含大写字母）
     * @param length
     * @return
     */
    public static String generateUpperString(int length) {
        return getString(length, UPPER_CHAR);
    }

    /**
     *  获取指定长度的随机数（只包含数字）
     * @param length
     * @return
     */
    public static String generateNumberString(int length) {
        return getString(length, NUMBER_CHAR);
    }

    private static String getString(int length, String chart) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) stringBuffer.append(chart.charAt(random.nextInt(chart.length())));
        return stringBuffer.toString();
    }
}
