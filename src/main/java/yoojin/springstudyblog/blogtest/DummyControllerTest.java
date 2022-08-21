package yoojin.springstudyblog.blogtest;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import yoojin.springstudyblog.model.RoleType;
import yoojin.springstudyblog.model.User;
import yoojin.springstudyblog.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //이 객체가 메모리에 올라갈 때 UserRepository도 같이 올라감
                //의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다";
        }
        return "삭제되었습니다. id : "+id;
    }


    @Transactional  //함수 종료시 자동 커밋 => 영속성 컨텍스트에 저장되어 있는 영속화된 데이터를 저장소에 반영
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json -> java.Object 변환 (MessageConverter의 Jackson 라이브러리)
        System.out.println("id: "+id);
        System.out.println("pw: "+requestUser.getPassword());
        System.out.println("email: "+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //더티 체킹
        //영속화된 데이터에 변경이 감지되면 DB를 수정함.

       //userRepository.save(requestUser);
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한 페이지당 2건(size) 데이터, id 최신순
    // ?page=n 으로 page 조절 가능
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2,sort="id", direction= Sort.Direction.DESC) Pageable pageable) {
        Page<User> paginUsers = userRepository.findAll(pageable);
        List<User> users = paginUsers.getContent();
        return users;
    }

    //{id} 주소로 파라미터를 전달받을 수 있음.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        //findById의 return type은 optional (데이터가 없을 경우 null이 반환되는데 그에 따른 에러를 막기 위해)
        User user =  userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당하는 유저가 없습니다. id : " + id);            }
        });
        return user;
    }



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



