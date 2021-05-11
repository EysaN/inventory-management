package me.geik.invmng;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "<h1>Welcome to the home page!</h1>";
    }

    @GetMapping("/level3")
    public String developer() {
        return "<h1>Welcome normal user!</h1>";
    }

    @GetMapping("/level2")
    public String user() {
        return "<h1>Welcome sub-admin!</h1>";
    }

    @GetMapping("/level1")
    public String admin() {
        return "<h1>Welcome admin!</h1>";
    }

}