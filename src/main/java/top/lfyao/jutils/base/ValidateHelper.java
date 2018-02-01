package top.lfyao.jutils.base;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 判断数组、字符串、集合、map、对象是否为空、不为空
 */
public class ValidateHelper {


    /**
     * 判断数组是否为空
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isEmptyArray(T[] array) {
        if (array == null || array.length == 0) return true;
        else return false;
    }


    /**
     * 判断数组是否不为空
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> boolean isNotEmptyArray(T[] array) {
        if (array != null && array.length > 0) return true;
        else return false;
    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmptyString(String s) {
        if (s == null || s.length() == 0) return true;
        else return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param s
     * @return
     */
    public static boolean isNotEmptyString(String s) {
        if (s != null && s.length() > 0) return true;
        else return false;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmptyCollection(Collection collection) {
        if (collection == null || collection.isEmpty()) return true;
        else return false;
    }

    /**
     * 判断集合是否不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmptyCollection(Collection collection) {
        if (collection != null && !collection.isEmpty()) return true;
        else return false;
    }

    /**
     * 判断map集合是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmptyMap(Map map) {
        if (map == null || map.isEmpty()) return true;
        else return false;
    }

    /**
     * 判断map集合是否不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmptyMap(Map map) {
        if (map != null && !map.isEmpty()) return true;
        else return false;
    }

    /**
     * 检验对象是否为空,String 中只有空格在对象中也算空
     *
     * @param object
     * @return 为空返回true, 否则false
     */
    public static boolean isEmpty(Object object) {
        if (null == object) return true;
        else if (object instanceof String) return "".equals(object.toString().trim());
        else if (object instanceof Iterable) return !((Iterable) object).iterator().hasNext();
        else if (object.getClass().isArray()) return Array.getLength(object) == 0;
        else if (object instanceof Map) return ((Map) object).size() == 0;
            //isAssignableFrom方法判断A是否是B的父类或者和B类型相同或者B实现了A接口.是类与类之间的比较
       /* else if (Number.class.isAssignableFrom(object.getClass())) return false;
        else if (Date.class.isAssignableFrom(object.getClass())) return false;*/
        else return false;
    }
}
