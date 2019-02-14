package com.wangchun.concurrency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *    @author  : wangchun
 *    @time    : 2019/2/14 21:20
 *    desc    : 输入描述
 *    version : v1.0
 * </pre>
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }

}
