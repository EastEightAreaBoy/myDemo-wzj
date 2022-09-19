package com.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HP
 */
@Slf4j
@RestController
public class FirstController {

    @RequestMapping("/time")
    public String getBook() {
        return System.currentTimeMillis() + "";
    }

}
