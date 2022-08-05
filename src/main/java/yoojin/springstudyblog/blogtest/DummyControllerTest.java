package yoojin.springstudyblog.blogtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import yoojin.springstudyblog.model.RoleType;
import yoojin.springstudyblog.model.User;
import yoojin.springstudyblog.repository.UserRepository;

@RestController
public class DummyControllerTest {

    @Autowired //이 객체가 메모리에 올라갈 때 UserRepository도 같이 올라감
                //의존성 주입(DI)
    private UserRepository userRepository;

    //http body에 username, password, email 데이터를 가지고 요청하게되면 돔영의 파라미터 변수에 자동으로 들어감.
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

}



