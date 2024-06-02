package khu.bigdata.infou.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TestController", description = "Swagger Test Controller")
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/example")
    public String example() {
        return "example for swagger test";
    }

}
