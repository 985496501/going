package redisTest;

import cn.yun.go.GoingApplication;
import cn.yun.go.entity.PermissionDTO;
import cn.yun.go.repository.PermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * use redis repository to access to entities.
 *
 * @author jinyun liu
 * @date 2020/5/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoingApplication.class)
public class RepositoryTest {
    @Autowired
    private PermissionRepository repository;

    @Test
    public void basicCrudTest() {
        PermissionDTO dto = new PermissionDTO(1L, 0L, "下单", "普通用户请求下单");
        // no transaction.
        PermissionDTO save = repository.save(dto);

        Optional<PermissionDTO> op = repository.findById("1");
        op.ifPresent(System.out::println);

        long count = repository.count();
        System.out.println(count);

        repository.delete(save);
    }



}
