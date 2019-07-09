package com.sporthealth.monitor;

import com.sporthealth.monitor.Enum.ClientEnum;

/**
 * @program: sporthealth
 * @description: Client工厂
 * @author: kongdingchao
 * @create: 2019-06-21 19:55
 **/
public interface ClientFactory {
    /**
     * 查找client
     * @return client
     */
    public Client findClient(String type);

    /**
     * 初始化
     */
    public void init();

    /**
     * 销毁
     */
    public void destroy();
}
