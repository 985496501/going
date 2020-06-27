package cn.yun.go;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://github.com/alibaba/jetcache/wiki/RedisWithLettuce_CN
 * 缓存服务继承写一个公共包，然后直接注入依赖就完事了。
 * 这里继承 jetcache 和 redis lettuce.
 *
 */
@SpringBootApplication
public class GoingApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoingApplication.class, args);
    }
}

@RestController
@RequiredArgsConstructor
class HelloController {
    private final HelloService helloService;

    @GetMapping("greet")
    public String greet() {
        return helloService.greet();
    }
}


@Service
class HelloService {
    public String greet() {
        return "hello world2";
    }
}