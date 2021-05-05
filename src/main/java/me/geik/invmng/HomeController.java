package me.geik.invmng;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "<h1>Welcome to the home page!</h1>";
    }

    @GetMapping("/developer")
    public String developer() {
        return "<h1>Welcome developer!</h1>";
    }

    @GetMapping("/submanager")
    public String user() {
        return "<h1>Welcome submanager!</h1>";
    }

    @GetMapping("/manager")
    public String admin() {
        return "<h1>Welcome manager!</h1>";
    }

}