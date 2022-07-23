package yoojin.springstudyblog.blogtest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class blogControllerTest {

    @GetMapping("/test/hello")
    public String Hello() {
        return "<h1>Hello Spring Blog</h1>";
    }

}
