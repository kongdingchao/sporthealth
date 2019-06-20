package com.sporthealth.common.utils;

import com.sporthealth.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Author: kongdingchao
* @Description: 读取配置类--适合区块模式
* @Date: 2019/6/19 21:55
*/
public class ConfigReader {
   private final static Logger logger = LoggerFactory.getLogger(ConfigReader.class);

    /**
     * 整个ini的引用
     */
    private Map<String,Map<String, List<String>>>  map = null;

    /**
     * 整个ini的引用_反向
     */
    private Map<String,Map<String, List<String>>>  map_reverse = null;
    
    /**
     * 当前Section的引用
     */
    private String currentSection = null;
    
    public ConfigReader(){};
    
    public final void initConfig(String path){
        map = new HashMap<String, Map<String,List<String>>>();
        map_reverse = new HashMap<String, Map<String,List<String>>>();
        try {
            BufferedReader reader =  new BufferedReader(new InputStreamReader(new FileInputStream(path), Constants.UTF8));
            read(reader);     
        } catch (IOException e) {
            logger.error("ConfigReader异常",e);
            
        }
    }
     
    /**
     * 读取
     * @param path
     * @throws IOException 
     */
    public ConfigReader(String path) {
        initConfig(path);
    }
 
    /**
     * 读取文件
     * @param reader
     * @throws IOException
     */
    private void read(BufferedReader reader) throws IOException {
        String line = null;
        while((line=reader.readLine())!=null) {
            parseLine(line);
        }
    }
     
    /**
     * 转换
     * @param line
     */
    private void parseLine(String line) {
        String line_str = line;
        line_str=line_str.trim();
        // 此部分为注释
        if(line_str.matches("^\\#.*$")) {
            return;
        }else if (line_str.matches("^\\[\\S+\\]$")) {
            // section
            String section = line_str.replaceFirst("^\\[(\\S+)\\]$","$1");
            addSection(map,section);
            addSection(map_reverse,section);
        }else if (line_str.matches("^\\S+=.*$")) {
            // key ,value
            int i = line_str.indexOf('=');
            String key = line_str.substring(0, i).trim();
            String value =line_str.substring(i + 1).trim();
            addKeyValue(map,currentSection,key,value);
            addKeyValue(map_reverse,currentSection,value,key);
        }
    }
 
 
    /**
     * 增加新的Key和Value
     * @param map
     * @param currentSection
     * @param key
     * @param value
     */
    private void addKeyValue(Map<String, Map<String, List<String>>> map,
            String currentSection,String key, String value) {
        if(!map.containsKey(currentSection)) {
            return;
        }
         
        Map<String, List<String>> childMap = map.get(currentSection);
         
        if(!childMap.containsKey(key)) {
            List<String> list = new ArrayList<String>();
            list.add(value);
            childMap.put(key, list);
        } else {
            childMap.get(key).add(value);
        }
    }
 
 
    /**
     * 增加Section
     * @param map
     * @param section
     */
    private void addSection(Map<String, Map<String, List<String>>> map,
            String section) {
        if (!map.containsKey(section)) {
            currentSection = section;
            Map<String,List<String>> childMap = new HashMap<String, List<String>>();
            map.put(section, childMap);
        }
    }
     
    /**
     * 获取配置文件指定Section和指定子键的值
     * @param section
     * @param key
     * @return
     */
    public List<String> get(String section,String key){
        if(map.containsKey(section)) {
            return  get(section).containsKey(key) ?
                    get(section).get(key): null;
        }
        return null;
    }
    public  String getOneValue(String section, String key) {
        List<String> list = get(section, key);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    /**
     * 获取配置文件指定Section和指定子键的值
     * @param section
     * @param key
     * @return
     */
    public List<String> getMapR(String section,String key){
        if(map_reverse.containsKey(section)) {
            return  getMapR(section).containsKey(key) ?
                    getMapR(section).get(key): null;
        }
        return null;
    }
     
    /**
     * 获取配置文件指定Section的子键和值
     * @param section
     * @return
     */
    public Map<String, List<String>> get(String section){
        return  map.containsKey(section) ? map.get(section) : null;
    }
    
    
   /**
    * 获取配置文件指定Section的子键和值
    * @param section
    * @return
    */
   public Map<String, List<String>> getMapR(String section){
       return  map_reverse.containsKey(section) ? map_reverse.get(section) : null;
   }
     
    /**
     * 获取这个配置文件的节点和值
     * @return
     */
    public Map<String, Map<String, List<String>>> get(){
        return map;
    }

    public Map<String, Map<String, List<String>>> getMap_reverse() {
        return map_reverse;
    }

    public void setMap_reverse(Map<String, Map<String, List<String>>> map_reverse) {
        this.map_reverse = map_reverse;
    }
     
}