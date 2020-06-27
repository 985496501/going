package cn.yun.go;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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