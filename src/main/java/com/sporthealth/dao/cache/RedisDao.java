package com.sporthealth.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.sporthealth.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * TODO
 **/
public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JedisPool jedisPool;

    private RuntimeSchema<UserInfo> schema = RuntimeSchema.createFrom(UserInfo.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public UserInfo getSeckill(long userId) {
        // redis操作业务逻辑
        try {
            Jedis jedis = jedisPool.getResource();
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
                return userInfo;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String putSeckill(UserInfo userInfo) {
        //  set Object(Seckill) -> 序列化 -> byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            String key = "userInfo:" + userInfo.getUserId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(userInfo, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            // 超时缓存
            int timeout = 60 * 60;
            String str =  jedis.setex(key.getBytes(), timeout, bytes);
            jedis.close();
            return str;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
