package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String testApi() {
        // This request will generate a Prometheus metric and a Zipkin trace
        return "Hello from the test API! Service registered with Consul.";
    }


    @PostMapping("/echo")
    public String echo(@RequestBody String input) {
        return "Echo: " + input;
    }
}
