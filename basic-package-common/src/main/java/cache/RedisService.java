package cache;

import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
    /**
     * publish 发布消息
     * @param channel
     * @param msg
     * @return
     */
    Long publish(String channel, String msg);

    /**
     * 订阅消息
     * @param jedisPubSub
     * @param channels
     */
    void subcribe(JedisPubSub jedisPubSub, String... channels);

    /**
     * key是否存在
     * @param key
     * @return
     */
    boolean exists(String key);

    void recordNewKeyToAllKeySet(String rkeyOfAllKeySet, String newRkey);

    /**
     * set
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * get
     * @param key
     * @return
     */
    String get(String key);

    /**
     * del
     * @param keys
     * @return
     */
    Long del(String... keys);

    /**
     * mget
     * @param keys
     * @return
     */
    List<String> mget(String... keys);

    /**
     * hset
     * @param hKey
     * @param vKey
     * @param val
     * @return
     */
    Long hset(String hKey, String vKey, String val);

    /**
     * hmset
     * @param key
     * @param valMap
     * @return
     */
    String hmset(String key, Map<String, String> valMap);

    /**
     * hmget
     * @param key
     * @param fields
     * @return
     */
    List<String> hmget(String key, String... fields);

    /**
     * hget
     * @param key
     * @param field
     * @return
     */
    String hget(String key, String field);

    /**
     * hexists
     * @param key
     * @param field
     * @return
     */
    Boolean hexists(String key, String field);

    /**
     * hgetAll
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);

    /**
     * hkeys
     * @param key
     * @return
     */
    Set<String> hkeys(String key);

    /**
     * sadd
     * @param key
     * @param vals
     * @return
     */
    Long sadd(String key, String... vals);

    /**
     * srem
     * @param key
     * @param member
     * @return
     */
    Long srem(String key, String... member);


    /**
     * smembers
     * @param key
     * @return
     */
    Set<String> smembers(String key);

    /**
     * sIsMember
     * @param key
     * @param member
     * @return
     */
    Boolean sIsMember(String key, String member);

    /**
     * sunion
     * @param keys
     * @return
     */
    Set<String> sunion(String... keys);

    /**
     * sunionstore
     * @param dstKey
     * @param key
     * @return
     */
    Long sunionstore(String dstKey, String... key);

    /**
     * blpop
     * @param timeout
     * @param key
     * @return
     */
    List<String> blpop(int timeout, String key);

    /**
     * rpush
     * @param key
     * @param values
     * @return
     */
    Long rpush(String key, String... values);

    /**
     * zadd
     * @param key
     * @param score
     * @param member
     * @return
     */
    Long zadd(String key, Double score, String member);

    /**
     * zadd
     * @param key
     * @param members
     * @return
     */
    Long zadd(String key, Map<String, Double> members);

    /**
     * zrangeByScore
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<String> zrangeByScore(String key, Double min, Double max);

    /**
     * zscore
     * @param key
     * @param member
     * @return
     */
    Double zscore(String key, String member);

    /**
     * zremrangeByScore
     * @param key
     * @param start
     * @param end
     * @return
     */
    Long zremrangeByScore(String key, Double start, Double end);

    /**
     *  zRevRangeByScore
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<String> zRevRangeByScore(String key, Double max, Double min);

    /**
     * zrank
     * @param key
     * @param member
     * @return
     */
    Long zrank(String key, String member);

    /**
     * zrange
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> zrange(String key, long start, long end);

    /**
     * zrangeByScoreWithScores
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<Tuple> zrangeByScoreWithScores(String key, long start, long end);

    Set<Tuple> zrangeByScoreWithScores(String key, Double min, Double max);

    /**
     * zRevRange
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> zRevRange(String key, long start, long end);

    /**
     * zRevRangeByScoreWithScores
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<Tuple> zRevRangeByScoreWithScores(String key, long start, long end);

    Long setNx(String key, String value);

    String getSet(String key, String value);

    Long expire(String key, int value);
}
