package top.lfyao.jutils.file;


import top.lfyao.jutils.date.DateUtils;
import top.lfyao.jutils.math.RandomUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * 文件工具类
 *
 * @author: mengJiangLi
 * @create: 2018-02-01 11:09
 **/
public class FileUtils {
    private static final String FOLDER_SEPARATOR = "/";
    private static final char EXTENSION_SEPARATOR = '.';

    /**
     * @Description: 判断指定路径是否存在，如果不存在，根据参数决定是否新建
     * @param: 指定的文件路径  isNew(true：新建、false：不新建)
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/2/1
     */
    public static boolean isExist(String filePath, boolean isNew) {
        File file = new File(filePath);
        if (!file.exists() && isNew) return file.mkdirs();  //新建文件路径
        return false;
    }

    /**
     * @Description: 获取文件名，构建结构为 prefix + yyyyMMddHHmmss + 10位随机数 + suffix + .type
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/2/1
     */
    public static String getFileName(String type, String prefix, String suffix) {
        String date = DateUtils.getCurrentTime("yyyyMMddHHmmss");
        // 10位随机数
        String random = RandomUtils.generateNumberString(10);
        return prefix + date + random + EXTENSION_SEPARATOR + type;
    }

    /**
     * @Description: 获取文件名，文件名构成:当前时间 + 10位随机数 + .type
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/2/1
     */
    public static String getFileName(String type) {
        return getFileName(type, "", "");
    }

    /**
     * @Description: 获取文件名，文件构成：当前时间 + 10位随机数
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/2/1
     */
    public static String getFileName() {
        String date = DateUtils.getCurrentTime("yyyyMMddHH24mmss");   //当前时间
        String random = RandomUtils.generateNumberString(10);   //10位随机数
        //返回文件名  
        return date + random;
    }

    /**
     * @Description: 获取指定文件的大小
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/2/1
     */
    public static long getFileSize(File file) {
        long size = 0;
        try {
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = fis.available();
            } else file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * @Description: 删除所有文件，包括文件夹
     * @param:
     * @return:
     * @Author: mengJiangLi
     * @Date: 2018/2/1
     */
    public void deleteAll(String dirpath) {
        File file = new File(dirpath);
        try {
            if (!file.exists()) return;//目录不存在 退出
            if (file.isFile()) {// 如果是文件删除
                file.delete();
                return;
            }
            File[] files = file.listFiles(); // 如果目录中有文件递归删除文件
            for (File f : files) deleteAll(f.getAbsolutePath());
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /** 
    * @Description:  复制文件或者文件夹
    * @param:    源文件 目的文件 是否覆盖文件
    * @return:  
    * @Author: mengJiangLi 
    * @Date: 2018/2/1 
    */ 
    public static void copy(File inputFile, File outputFile, boolean isOverWrite) {
        try {
            if (!inputFile.exists()) throw new RuntimeException(inputFile.getPath() + "源目录不存在");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // 复制文件或者文件夹
    private static void copyPri(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
        if (inputFile.isFile()) {		//文件
            copySimpleFile(inputFile, outputFile, isOverWrite);
        } else {
            if (!outputFile.exists()) {		//文件夹
                outputFile.mkdirs();
            }
            // 循环子文件夹
            for (File child : inputFile.listFiles()) {
                copy(child, new File(outputFile.getPath() + "/" + child.getName()), isOverWrite);
            }
        }
    }
    /** 
    * @Description:  复制单个文件
    * @param:   源文件 目的文件 是否覆盖
    * @return:  
    * @Author: mengJiangLi 
    * @Date: 2018/2/1 
    */ 
    private static void copySimpleFile(File inputFile, File outputFile,
                                       boolean isOverWrite) throws IOException {
        if (outputFile.exists()) {
            if (isOverWrite) {		//可以覆盖
                if (!outputFile.delete()) {
                    throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
                }
            } else {
                // 不允许覆盖
                return;
            }
        }
        InputStream in = new FileInputStream(inputFile);
        OutputStream out = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        in.close();
        out.close();
    }

    public static String getFileMD5(File file) {
        if (!file.exists()|| !file.isFile())return null;
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest= digest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len=in.read(buffer,0,1024))!=-1)digest.update(buffer,0,len);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, digest.digest());
        return bigInteger.toString(16);
    }
    /** 
    * @Description:  获取文件的后缀
    * @param:   
    * @return:  
    * @Author: mengJiangLi 
    * @Date: 2018/2/1 
    */ 
    public static String getFileSuffix(String file) {
        if (file==null)return null;
        int endIndex = file.lastIndexOf(EXTENSION_SEPARATOR);
        if (endIndex==-1)return null;
        int folderIndex = file.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > endIndex) return null;
        return file.substring(endIndex + 1);
    }
    /** 
    * @Description:  文件重命名
    * @param:   
    * @return:  
    * @Author: mengJiangLi 
    * @Date: 2018/2/1 
    */ 
    public boolean renameDir(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        return oldFile.renameTo(newFile);
    }
}
