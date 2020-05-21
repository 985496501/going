package redisTest;

import cn.yun.go.GoingApplication;
import cn.yun.go.entity.RoleDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * HashMapper is intended for using with Redis Hashes.
 *
 * @author jinyun liu
 * @date 2020/5/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoingApplication.class)
public class HashTest {

    @Resource(name = "redisTemplate")
    private HashOperations<String, byte[], byte[]> hashOperations;

    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

    @Test
    public void writeTest() {
        RoleDTO roleDTO = new RoleDTO(1L, 0L, LocalDateTime.now(), 0, "normalUser", "普通用户角色");

        Map<byte[], byte[]> map = mapper.toHash(roleDTO);
        hashOperations.putAll("role", map);
    }

    @Test
    public void loadTest() {
        Map<byte[], byte[]> role = hashOperations.entries("role");
        RoleDTO roleDTO = (RoleDTO) mapper.fromHash(role);
        System.out.println(roleDTO);
    }


}
