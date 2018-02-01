package top.lfyao.jutils.clone;

import top.lfyao.jutils.pojo.User;

import java.io.*;
import java.util.Collection;
import java.util.List;

/**
 * 克隆工具类，进行深克隆,包括对象、集合
 *
 * @author: mengJiangLi
 * @create: 2018-01-31 20:05
 **/
public class CloneUtils {
    /**
     * @Description: 采用对象的序列化完成对象的深克隆
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/1/31
     */
    public static <T extends Serializable> T cloneObject(T obj) {
        T cloneObj = null;

        try {
            // 写入字节流
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(obj);
            oos.close();
            // 分配内存，写入原始对象，生成新对象
            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            // 返回生成的新对象
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObj;
    }

    public static <T> Collection<T> cloneCollection(Collection<T> collection) {
        Collection<T> dest = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(collection);
            oos.close();

            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            dest = (Collection<T>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dest;

    }

}
