package redis_client.jedis;

import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * TODO
 *
 * @author fengtingting
 * @version 1.0
 * @date 2020/12/26 19:10
 */
public class JedisClusterClient {

    public static void main(String[] args) {
        /*Jedis*/
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("127.0.0.1",6379));


        /*spring jedis*/
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        clusterConfiguration.addClusterNode(new RedisNode("127.0.0.1", 6379));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(clusterConfiguration);

        RedisTemplate<String, String> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        redisTemplate.opsForValue().set("key","value");
    }
}
