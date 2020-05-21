package cn.yun.go.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author jinyun liu
 * @date 2020/5/18
 */
@Configuration
public class RedisConfig {

    /**
     * create a new Lettuce connection factory.
     *
     * @return redis connection factory.
     */
    @Bean(name = "redisFactory")
    public LettuceConnectionFactory redisConnectionFactory() {
        // new RedisStandaloneConfiguration("server", 6379)
        RedisSentinelConfiguration sentinelConfiguration = new RedisSentinelConfiguration()
                .master("redis-master")
                .sentinel("127.0.0.1", 6379)
                .sentinel("127.0.0.1", 6380);
        // propertySource:
        return new LettuceConnectionFactory();
    }

    /**
     * set up ReactiveRedisConnectionFactory for Lettuce.
     *
     * @return
     */
    // @Bean("reactiveRedisConnectionFactory")
    public ReactiveRedisConnectionFactory connectionFactory() {
        LettuceClientConfiguration config = LettuceClientConfiguration.builder()
                .useSsl().and()
                .commandTimeout(Duration.ofSeconds(2))
                .shutdownTimeout(Duration.ZERO)
                .build();

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379), config);
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisFactory);
        return stringRedisTemplate;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisFactory, RedisSerializer fastJsonRedisSerializer) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisFactory);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "fastJsonRedisSerializer")
    public RedisSerializer fastJsonRedisSerializer() {
        return new FastJsonRedisSerializer<>(Object.class);
    }

    @Bean(name = "redisTemplateForRepository")
    public RedisTemplate<?, ?> redisTemplateForRepository(RedisConnectionFactory redisFactory) {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisFactory);
        return template;
    }

}
