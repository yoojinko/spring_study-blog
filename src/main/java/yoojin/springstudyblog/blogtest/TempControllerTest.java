package yoojin.springstudyblog.blogtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //return값이 파일.
public class TempControllerTest {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        //파일 리턴 기본 경로 : src/main/resources/static
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg() {
        return "/a.png";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        //prefix : /WEB-INF/view/
        //suffix : .jsp

        return "test";
    }
}
