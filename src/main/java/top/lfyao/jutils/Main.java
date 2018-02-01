package top.lfyao.jutils;


import jdk.nashorn.internal.parser.JSONParser;
import top.lfyao.jutils.base.StringUtils;
import top.lfyao.jutils.clone.CloneUtils;
import top.lfyao.jutils.date.DateUtils;
import top.lfyao.jutils.pojo.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        chineseWord();
//        cloneTest();
        String yyyyMMddHH24mmss = DateUtils.getCurrentTime("yyyyMMddHH24mmss");
        long l = System.currentTimeMillis();


        System.out.println();

    }
    // char 测试

    private static void charTest() {
        int length = "".length();
        char c1 = "abc".charAt(2);
        int length1 = "abc".length();
        System.out.println("");
    }
    // 克隆测试
    public static void cloneTest() {
        List<User> users = new ArrayList<>();
        User a = new User(1, "a");
        User b = new User(2, "b");
        User c = new User(3, "c");
        User d = new User(4, "d");
        users.add(a);
        users.add(b);
        users.add(c);
        users.add(d);
//        User user = CloneUtils.cloneObject(a);
        Collection<User> collection = CloneUtils.cloneCollection(users);
        a.setName("aclone");
        b.setAge(200);
        c.setName("fsdlfhdslfj");
        System.out.println(collection);
    }

    /**
     * 判断中文字 测试
     */
    private static void chineseWord() {
        char a = 1;
        char b = '#';
        char c = '；';
        char d = '。';
        char e = '刘';
        char f = 'e';
        System.out.println(StringUtils.isChineseByScript(a));
        System.out.println(StringUtils.isChineseByScript(b));
        System.out.println(StringUtils.isChineseByScript(c));
        System.out.println(StringUtils.isChineseByScript(d));
        System.out.println(StringUtils.isChineseByScript(e));
        System.out.println(StringUtils.isChineseByScript(f));
        System.out.println("---------------------------------------------------");
        System.out.println(StringUtils.isChinesePunctuation(a));
        System.out.println(StringUtils.isChinesePunctuation(b));
        System.out.println(StringUtils.isChinesePunctuation(c));
        System.out.println(StringUtils.isChinesePunctuation(d));
        System.out.println(StringUtils.isChinesePunctuation(e));
        System.out.println(StringUtils.isChinesePunctuation(f));

    }
}
