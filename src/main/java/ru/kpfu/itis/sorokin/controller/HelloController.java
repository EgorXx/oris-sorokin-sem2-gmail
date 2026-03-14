package ru.kpfu.itis.sorokin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.sorokin.service.HelloService;
import ru.kpfu.itis.sorokin.service.UserService;

@RestController
public class HelloController {

    private final HelloService helloService;
    private final UserService userService;

    public HelloController(HelloService helloService, UserService userService) {
        this.helloService = helloService;
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false, name = "name", defaultValue = "labubu") String name) {
        return helloService.sayHello(name);
    }
}
