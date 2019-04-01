package cache;

import interfaces.Cons;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.coyote.http11.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
@Service("redisServiceImpl")
@EnableConfigurationProperties(RedisProperties.class)
public class RedisServiceImpl implements RedisService {
    private GenericObjectPoolConfig poolConfig;
    private HashSet<HostAndPort> nodes;
    private JedisCluster jedisCluster;

    @Autowired
    public RedisServiceImpl(RedisProperties redisProperties) {
        poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getMinIdle());
        poolConfig.setMaxTotal(redisProperties.getMaxTotal());
        poolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        nodes = new HashSet<>();

        List<String> redisIntanceUrlList = redisProperties.getIntanceUrlList();
        for (String url : redisIntanceUrlList) {
            String[] urlarr = url.split(Cons.COLON);
            if (urlarr.length == 2) {
                nodes.add(new HostAndPort(urlarr[0], Integer.parseInt(urlarr[1])));
            }
        }

        jedisCluster = new JedisCluster(nodes, redisProperties.getTimeout(), poolConfig);
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    @Override
    public void recordNewKeyToAllKeySet(String rkeyOfAllKeySet, String newRkey) {
        if(!sIsMember(rkeyOfAllKeySet,
                newRkey)) {
            long saddRet = sadd(rkeyOfAllKeySet, newRkey);
            log.info("sadd newkey {} to {} allKeySet, ret: {}",newRkey, rkeyOfAllKeySet, saddRet);
        }
    }

    @Override
    public String set(String key, String value) {
        return this.jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return this.jedisCluster.get(key);
    }

    @Override
    public Long del(String... keys) {
        return this.jedisCluster.del(keys);
    }

    @Override
    public List<String> mget(String... keys) {
        return this.jedisCluster.mget(keys);
    }

    @Override
    public Long hset(String hKey, String vKey, String val) {
        return this.jedisCluster.hset(hKey, vKey, val);
    }

    @Override
    public String hmset(String key, Map<String, String> valMap) {
        return this.jedisCluster.hmset(key, valMap);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return this.jedisCluster.hmget(key, fields);
    }

    @Override
    public String hget(String key, String field) {
        return this.jedisCluster.hget(key, field);
    }

    @Override
    public Boolean hexists(String key, String field) {
        return this.jedisCluster.hexists(key, field);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        return this.jedisCluster.hgetAll(key);
    }

    @Override
    public Set<String> hkeys(String key) {
        return this.jedisCluster.hkeys(key);
    }

    @Override
    public Long sadd(String key, String... vals) {
        return this.jedisCluster.sadd(key, vals);
    }

    @Override
    public Long srem(String key, String...member) {
        return this.jedisCluster.srem(key, member);
    }
    @Override
    public Boolean sIsMember(String key, String member) {
        return this.jedisCluster.sismember(key, member);
    }

    @Override
    public boolean exists(String key) {
        return this.jedisCluster.exists(key);
    }

    @Override
    public Long publish(String channel, String msg) {
        return this.jedisCluster.publish(channel, msg);
    }

    @Override
    public void subcribe(JedisPubSub jedisPubSub, String... channels) {
        this.jedisCluster.subscribe(jedisPubSub, channels);
    }

    @Override
    public Set<String> smembers(String key) {
        return this.jedisCluster.smembers(key);
    }

    @Override
    public Set<String> sunion(String... keys) {
        return this.jedisCluster.sunion(keys);
    }

    @Override
    public Long sunionstore(String dstKey, String...key) {
        return this.jedisCluster.sunionstore(dstKey, key);
    }

    @Override
    public List<String> blpop(int timeout, String key) {
        return this.jedisCluster.blpop(timeout, key);
    }

    @Override
    public Long rpush(String key, String... values) {
        return this.jedisCluster.rpush(key, values);
    }

    @Override
    public Long zadd(String key, Double score, String member) {
        return this.jedisCluster.zadd(key, score, member);
    }

    @Override
    public Long zadd(String key, Map<String, Double> members) {
        return this.jedisCluster.zadd(key, members);
    }

    @Override
    public Double zscore(String key, String member) {
        return jedisCluster.zscore(key, member);
    }

    @Override
    public Set<String> zrangeByScore(String key, Double min, Double max) {
        return this.jedisCluster.zrangeByScore(key, min, max);
    }

    @Override
    public Long zremrangeByScore(String key, Double start, Double end) {
        return this.jedisCluster.zremrangeByScore(key, start, end);
    }

    @Override
    public Set<String> zRevRangeByScore(String key, Double max, Double min) {
        return this.jedisCluster.zrevrangeByScore(key, max, min);
    }



    @Override
    public Long zrank(String key, String member) {
        return this.jedisCluster.zrank(key, member);
    }

    @Override
    public Set<String> zrange(String key, long start, long end) {
        return this.jedisCluster.zrange(key, start, end);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, long start, long end) {
        return jedisCluster.zrangeWithScores(key, start, end);
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(String key, Double min, Double max) {
        return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
    }

    @Override
    public Set<String> zRevRange(String key, long start, long end) {
        return this.jedisCluster.zrevrange(key, start, end);
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(String key, long start, long end) {
        return this.jedisCluster.zrevrangeWithScores(key, start, end);
    }

    @Override
    public Long setNx(String key, String value) {
        return this.jedisCluster.setnx(key, value);
    }

    @Override
    public String getSet(String key, String value) {
        return this.jedisCluster.getSet(key, value);
    }

    @Override
    public Long expire(String key, int value) {
        return this.jedisCluster.expire(key, value);
    }
}
