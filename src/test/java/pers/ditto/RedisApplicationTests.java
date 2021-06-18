package pers.ditto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import pers.ditto.entity.User;
import pers.ditto.utils.RedisUtils;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {

        // opsForValue()操作String
        // ValueOperations valueOperations = redisTemplate.opsForValue();

        // opsForList()操作List
        // ListOperations listOperations = redisTemplate.opsForList();

        // 获取redis的连接对象
        // RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();


        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();
        redisTemplate.opsForValue().set("sun","orange");
        System.out.println(redisTemplate.opsForValue().get("sun"));
        // connection.flushAll();

    }

    @Test
    public void testUser() throws JsonProcessingException {

        // 开发中一般使用json来传递对象
        User user = new User("sun", 7);
        String jsonUser = new ObjectMapper().writeValueAsString(user);

        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();
        redisTemplate.opsForValue().set("user",jsonUser);
        System.out.println(redisTemplate.opsForValue().get("user"));
        // connection.flushAll();
    }

    @Test
    public void testUtils(){

        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushAll();
        redisUtils.set("sun","orange");
        System.out.println(redisUtils.get("sun"));
        connection.flushAll();
    }

}
