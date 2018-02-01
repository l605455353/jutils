package top.lfyao.jutils.pojo;

import java.io.Serializable;

/**
 * @author: mengJiangLi
 * @create: 2018-01-31 19:29
 **/
public class User implements Serializable{

    private static final long serialVersionUID = -7680346923692225585L;
    private int age;
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getwanghai() {
        System.out.println("wanghai");
    }
}
