package yoojin.springstudyblog.blogtest;

import org.springframework.web.bind.annotation.*;

//@Controller : 사용자가 요청 -> 응답 (HTML 파일)
//@RestController : 사용자가 요청 -> 응답 (Data)

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest: ";
    @GetMapping("/http/lombok")
    public String lombokTest() {
        //Member m = new Member(1,"ssar","1234","Email");
        Member m = Member.builder().username("ssar").password("1234").email("Email").build();   //생성자에 @Builder가 있으면 사용 가능
        System.out.println(TAG+"getter: "+m.getId());
        m.setId(5000);
        System.out.println(TAG+"setter: "+m.getId());
        return "lombok test 완료";
    }
    //인터넷 브라우저 요청은 무조건 get 요청임.
    //postman으로 요청하면 가능
    @GetMapping("/http/get")
    public String getTest(Member m){

        return "get 요청 : " + m.getId() + "," + m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m){
        return "post 요청 : " + m.getId() + "," + m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }
    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
