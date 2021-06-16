package com.springboot.rabbitmq.springbootxrabbitmq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbit/consumer")
public class ConsumerController {

    @RequestMapping(value = "consumer")
    public String consumerMessage() {
        return "consumerMessage success";
    }
}
