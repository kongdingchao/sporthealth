package com.sporthealth.common.config;

import com.sporthealth.common.utils.PropertiesLoader;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: sporthealth
 * @description: 全局配置(懒汉)
 * @author: kongdingchao
 * @create: 2019-06-16 15:00
 **/
public class Global {
    private Global() {}

    /**
     * 当前对象实例
     */
    private static Global global = null;

    /**
     * 静态工厂方法 获取当前对象实例 多线程安全单例模式(使用双重同步锁)
     */

    public static synchronized Global getInstance() {

        if (global == null) {
            synchronized (Global.class) {
                if (global == null)
                    global = new Global();
            }
        }
        return global;
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("jeesite.properties");
    /**
     * 获取配置
     *
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

}
