package top.lfyao.jutils.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类，对字符串进行常规的处理
 */
public class StringUtils {

    /**
     * 将半角的符号转换成全角符号.(即英文字符转中文字符)
     *
     * @param str
     * @return
     */
    public static String changeToFull(String str) {
        String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";
        String[] decode = {"１", "２", "３", "４", "５", "６", "７", "８", "９", "０",
                "！", "＠", "＃", "＄", "％", "︿", "＆", "＊", "（", "）", "ａ", "ｂ",
                "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ",
                "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ",
                "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ",
                "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ",
                "Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；", "：",
                "'", "\"", "，", "〈", "。", "〉", "／", "？"};
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int pos = source.indexOf(str.charAt(i));
            if (pos != -1) result += decode[pos];
            else result += str.charAt(i);
        }
        return result;
    }

    /**
     * 将字符转换为编码为Unicode，格式 为'\u0020'<br>
     * unicodeEscaped(' ') = "\u0020"<br>
     * unicodeEscaped('A') = "\u0041"
     *
     * @param ch
     * @return
     */
    public static String unicodeEscaped(char ch) {
        if (ch < 0x10) return "\\u000" + Integer.toHexString(ch);
        else if (ch < 0x100) return "\\u00" + Integer.toHexString(ch);
        else if (ch < 0x1000) return "\\u0" + Integer.toHexString(ch);
        else return "\\u" + Integer.toHexString(ch);
    }

    /**
     * 进行toString操作，若为空，返回默认值
     *
     * @param object  要进行toString操作的对象
     * @param nullStr 返回的默认值
     * @return
     */
    public static String toString(Object object, String nullStr) {
        return object == null ? nullStr : object.toString();
    }

    /**
     * 将某个字符重复N次
     *
     * @param ch    需要循环的字符
     * @param count 循环的次数
     * @return
     */
    public static String repeatChar(char ch, int count) {
        char[] buf = new char[count];
        for (int i = 0; i < count; i++) buf[i] = ch;
        return new String(buf);
    }

    /**
     * 将字符串重复N次，null、""不在循环次数里面,当count <= 1 返回value
     *
     * @param value 需要循环的字符串
     * @param count 循环的次数
     * @return
     */
    public static String repeatString(String value, int count) {
        if (value == null || "".equals(value) || count <= 1) return value;
        int length = value.length();

        if (length == 1) {
            return repeatChar(value.charAt(0), count);
        } else {
            int newLength = length * count;
            StringBuilder builder = new StringBuilder(newLength);
            for (int i = 0; i < count; i++) builder.append(value);
            return builder.toString();
        }

    }

    /**
     * 根据Unicode编码完美的判断中文汉字 参考资料：http://www.cnblogs.com/zztt/p/3427452.html
     *
     * @param c
     * @return
     */
    public static boolean isChineseByScript(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    /**
     * 根据UnicodeBlock方法判断中文标点符号 参考资料：http://www.cnblogs.com/zztt/p/3427452.html
     *
     * @param c
     * @return
     */
    public static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否全部都为小写
     *
     * @param value
     * @return
     */
    public static boolean isAllLowerCase(String value) {
        if (ValidateHelper.isEmptyString(value)) return false;
        for (int i = 0; i < value.length(); i++) if (Character.isLowerCase(value.charAt(i)) == false) return false;
        return true;
    }

    /**
     * 判断字符串是否全部大写
     *
     * @param value
     * @return
     */
    public static boolean isAllUpperCase(String value) {
        if (ValidateHelper.isEmptyString(value)) return false;
        for (int i = 0; i < value.length(); i++) if (Character.isUpperCase(value.charAt(i)) == false) return false;
        return true;
    }

    /**
     * 反转字符串
     *
     * @param value
     * @return
     */
    public static String reverse(String value) {
        if (value == null) return null;
        return new StringBuilder(value).reverse().toString();
    }

    /**
     * 过滤html标签，包括script、style、html、空格、回车标签
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签

        return htmlStr.trim(); // 返回文本字符串
    }

}
