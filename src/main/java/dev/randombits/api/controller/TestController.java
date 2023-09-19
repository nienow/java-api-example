package dev.randombits.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/welcome", produces = "text/plain")
    public String welcome() {
        return "Welcome to my app";
    }

    @GetMapping(value = "/restricted", produces = "text/plain")
    public String restricted() {
        return "This is restricted content";
    }
}
