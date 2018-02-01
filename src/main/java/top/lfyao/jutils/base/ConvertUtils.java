package top.lfyao.jutils.base;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换工具类:若待转换值为null或者出现异常，则使用默认值
 */
public class ConvertUtils {

    /**
     * @Description: 日期转换为字符串
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/1/31
     */
    public static Date strToDate(String str) {
        return strToDate(str, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @Description: 日期转换为指定格式的字符串
     * @param: 待转换的日期  指定格式
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/1/31
     */
    public static Date strToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date parse = null;
        try {
            if (str != null)
                parse = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
    /** 
    * @Description: 日期转换为指定格式的字符串
    * @param:   
    * @return:  
    * @Author: mengJiangLi 
    * @Date: 2018/1/31 
    */ 
    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    /**
     * @Description:  Bean转换为Map
     * @param:
     * @return:  String-Object的HashMap
     * @Author: mengJiangLi
     * @Date: 2018/1/31
     */
    public static Map<String,Object> beanToMapObject(Object object){
        if(object == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);

                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * @Description:  Map转换为Java Bean
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/1/31
     */
    public static Object mapToBean(Map map,Object object){
        if(map == null || object == null){
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(object, value);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
