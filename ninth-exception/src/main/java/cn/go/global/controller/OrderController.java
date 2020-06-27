package cn.go.global.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinyun liu
 * @date 2020/6/3
 */
@RestController
public class OrderController {

    @GetMapping("say")
    public String sayHello() {
        return "hello world";
    }

}
