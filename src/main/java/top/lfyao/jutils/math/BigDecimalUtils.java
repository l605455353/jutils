package top.lfyao.jutils.math;


import java.math.BigDecimal;

/**
 * 提供精确的加减乘除运算
 */
public class BigDecimalUtils {

    /**
     * 默认保留位：2
     */
    private static int 	DEFAULT_SCALE = 2;

    /**
     * 默认四舍五入规则为：向上舍入(>=5进位）
     * BigDecimal.ROUND_DOWN(>5才进位）
     */
    private static int DEFAULT_ROUND = BigDecimal.ROUND_HALF_UP;



    /**
     * 加法运算
     * @param v1 加数
     * @param v2 被加数
     * @return
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }

    /**
     *  除法运算<br>
     * 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     * @param v1  除数
     * @param v2  被除数
     * @param scale 精确精度
     * @param round
     * @return
     */
    public static String div(String v1, String v2, int scale, int round) {
        if (scale<0) throw new IllegalArgumentException("The scale must be a positive integer or zero");
        if (scale==0)scale=DEFAULT_SCALE;
        if (round==0)round=DEFAULT_ROUND;
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, round).toString();
    }

    /**
     * 比较两数
     * @param v1
     * @param v2
     * @return
     */
    public static int compareTo(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.compareTo(b2);
    }

    /**
     * 返回较小数
     * @param v1
     * @param v2
     * @return
     */
    public static String returnMin(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.min(b2).toString();
    }

    /**
     * 返回较大数
     * @param v1
     * @param v2
     * @return
     */
    public static String returnMax(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.max(b2).toString();
    }

    /**
     * 处理BigDecimal数据，保留scale位小数
     * @param value
     * @param scale
     * @return
     */
    public static BigDecimal getValue(BigDecimal value,int scale){
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 将object转换为Bigdecimal
     * @param value 待转换的数值
     * @return
     */
    public static BigDecimal getBigDecimal(Object value){
        BigDecimal resultValue ;
        if(value instanceof String)resultValue =  new BigDecimal((String)value);
        else if(value instanceof Integer)resultValue =  new BigDecimal((Integer)value);
        else if(value instanceof Long)resultValue =  new BigDecimal((Long)value);
        else if(value instanceof Double)resultValue =  new BigDecimal((Double)value);
        else resultValue = (BigDecimal) value;
        return resultValue;
    }

}
