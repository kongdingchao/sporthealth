package com.sporthealth.modules.search.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.sporthealth.modules.search.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Redis缓存
 **/
public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //private final JedisPool jedisPool;
    private static ShardedJedisPool pool;

    private final RuntimeSchema<UserInfo> schema = RuntimeSchema.createFrom(UserInfo.class);

    public RedisDao(String ip, int port, String passwd) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(3000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //jedisPool = new JedisPool(ip, port);
        // 集群
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo(ip, port);
        jedisShardInfo1.setPassword(passwd);
        List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
        list.add(jedisShardInfo1);
        pool = new ShardedJedisPool(config, list);
    }

    public UserInfo getUserInfo(long userId) {
        // redis操作业务逻辑
        try {
            ShardedJedis jedis = pool.getResource();
            String key = "userInfo:" + userId;
            // 并没有实现内部序列化操作
            //get->byte[]字节数组->反序列化>Object(Seckill)
            // 采用自定义的方式序列化
            // 缓存获取到
            byte[] bytes = jedis.get(key.getBytes());

            jedis.close();
            if (bytes != null) {
                // 空对象
                UserInfo userInfo = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, userInfo, schema);
                // userInfo被反序列化
                logger.debug("jedis.get:key={},userInfo={}", key, userInfo);
                return userInfo;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public void putUserInfo(UserInfo userInfo) {
        //  set Object(UserInfo) -> 序列化 -> byte[]
        try {
            ShardedJedis jedis = pool.getResource();
            String key = "userInfo:" + userInfo.getUserId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(userInfo, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            // 超时缓存
            int timeout = 60 * 60;
            String str =  jedis.setex(key.getBytes(), timeout, bytes);
            logger.debug("jedis.setex:key={},userInfo={}", key, userInfo);
            jedis.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
