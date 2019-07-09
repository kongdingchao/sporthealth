package com.sporthealth.monitor;

import com.sporthealth.monitor.Enum.ClientEnum;
import com.sporthealth.monitor.impl.IntervalMonitorClient;
import com.sporthealth.monitor.impl.MonitorClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.List;


/**
 * @program: sporthealth
 * @description: spring生命周期管理
 * @author: kongdingchao
 * @create: 2019-06-20 21:23
 **/
public class SpringLifecycleManager implements InitializingBean,DisposableBean {
    private final static Logger logger = LoggerFactory.getLogger(SpringLifecycleManager.class);
    public static final ClientFactory monitorFactory = MonitorClientFactory.getSingleton();
    private List<Client> clients = new ArrayList<Client>();
    boolean isRealGather = false;
    boolean isIntervalGather = false;
    long intervalGatherTime = 0;

    @Override
    public void destroy() throws Exception {
        for (Client client : clients) {
            client.stop();
        }
        monitorFactory.destroy();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        try {
            if (isRealGather) {
                logger.info("StartRealGather...");
                clients.add(monitorFactory.findClient(ClientEnum.Real.name()));
            }

            if (isIntervalGather) {
                logger.info("StartIntervalGather...");
                IntervalMonitorClient client = (IntervalMonitorClient)monitorFactory.findClient(ClientEnum.Interval.name());
                clients.add(client);
            }

            for (Client client : clients) {
                client.start();
            }
        } catch (Exception e) {
            logger.error("start gather fail.", e);
            destroy();
        } finally {
        }
    }

    public void setRealGather(boolean realGather) {
        isRealGather = realGather;
    }

    public void setIntervalGather(boolean intervalGather) {
        isIntervalGather = intervalGather;
    }

    public void setIntervalGatherTime(long intervalGatherTime) {
        this.intervalGatherTime = intervalGatherTime;
    }

    public List<Client> getClients() {
        return clients;
    }
}
