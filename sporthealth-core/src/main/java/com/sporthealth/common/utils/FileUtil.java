package com.sporthealth.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @Author: kongdingchao
* @Description: 文件读写工具
* @Date: 2019/6/19 21:58
*/
public final class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    };

    public static void writeFile(String path, String str) throws IOException {

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");

        out.write(str);
        out.flush();
        out.close();
    }

    // 读取文件
    public static String readFile(String path) throws IOException {
        File file = new File(path);

        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;

        StringBuffer strBuffer = new StringBuffer();
        try {
            InputStream inputStream = new FileInputStream(file);
            inputReader = new InputStreamReader(inputStream, "utf-8");
            bufferReader = new BufferedReader(inputReader);

            // 读取一行
            String line = null;

            while ((line = bufferReader.readLine()) != null) {
                strBuffer.append(line).append('\n');

            }

        } catch (IOException e) {
            logger.error("readFile异常", e);
        } finally {
            if (bufferReader != null) {
                bufferReader.close();
            }
            if (inputReader != null) {
                inputReader.close();
            }
        }
        return strBuffer.toString();
    }

    public static String read(String path, String encoding) throws IOException {

        File file = new File(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        String line = null;
        StringBuffer sbf = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            sbf.append(line).append('\n');
        }
        reader.close();
        return sbf.toString();
    }

    // 在数组中增加一个元素
    public static String[] addSz(String[] strs, String first) {

        // 增加ruby
        List<String> list =  Arrays.asList(strs);
        list.add(first);
   
        String[] newStr = list.toArray(new String[1]); // 返回一个包含所有对象的指定类型的数组

        return newStr;
    }

    // 判断是否被修改过
    public static boolean isFileModified(File file, Long lastModified) {
        boolean returnValue = false;
        if (file.lastModified() > lastModified) {
            returnValue = true;
        }
        return returnValue;
    }

    public static List<String> findMegStrReadFile(String path,String startStr) throws IOException {
        File file = new File(path);

        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;

        List<String> result = new ArrayList<String>();
        try {
            InputStream inputStream = new FileInputStream(file);
            inputReader = new InputStreamReader(inputStream, "utf-8");
            bufferReader = new BufferedReader(inputReader);

            // 读取一行
            String line = null;

            while ((line = bufferReader.readLine()) != null) {
                // System.out.println(line);

                if (line.indexOf(startStr) != -1) {
                    String msg = line.substring(line.indexOf(startStr) , line.length());
                    result.add(msg);
                   //  System.out.println(msg);
                }

            }

        } catch (IOException e) {
            logger.error("findMegStrReadFile异常",e);
      
        } finally {
            if (bufferReader != null) {
                bufferReader.close();
            }
            if (inputReader != null) {
                inputReader.close();
            }

        }
        return result;
    }
}
