import cn.yun.go.GoingApplication;
import cn.yun.go.entity.UserDTO;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

/**
 * @author jinyun liu
 * @date 2020/5/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoingApplication.class)
public class GoingApplicationTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> template;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOperations;

    @Test
    public void redisTest() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name2", "jinyun23 liu");
        assert 1 == 1;
    }

    @Test
    public void getTest() {
        // 一个列表:  2 32 - 1   42亿9千万
        Long size = listOps.size("123456789");
        System.out.println("列表的长度: " + size);

        List<String> range = listOps.range("123456789", 0, 9);
        range.forEach(x -> System.out.println("列表的元素:" + x));
    }


    @Test
    public void objectTest() {
        List<Object> objects = listOperations.range("user", 0, 9);
        objects.forEach(x -> System.out.println("列表的元素:" + x));
        List<String> user = listOps.range("user", 0, 9);
        user.forEach(x -> System.out.println("列表的元素:" + x));

    }

    @Test
    public void listOpsTest() {
        // left push.
        listOps.leftPush("12345", "www.baidu.com23");
    }

    @Test
    public void getOpsTest() {
        ListOperations<String, String> opsForList = template.opsForList();
    }

    /**
     * 还是存储list
     */
    @Test
    public void ListObjTest() {
        listOperations.leftPushAll("user", new UserDTO("刘津运", 25, LocalDateTime.now()),
                new UserDTO("琳琳", 20, LocalDateTime.now()));
    }

    @Test
    public void stringTest() {
        listOps.leftPushAll("user", JSONObject.toJSONString(new UserDTO("刘津运", 25, LocalDateTime.now())));
    }

    @Test
    public void localDateTest() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        LocalDate localDate = LocalDate.of(1996, Month.JULY, 14); // 1996-07-14
        // LocalDateTime == timestamp; LocalDate == date; LocalTime == time
       // new UserDTO("1", 1, localDate);
        String format = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println(format); // 2020年5月19日 星期二
        System.out.println(localDate);
    }

}
