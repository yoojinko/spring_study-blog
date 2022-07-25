package yoojin.springstudyblog.blogtest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Getter
//@Setter
@Data
@NoArgsConstructor //빈 생성자
public class Member {
    private int id;   //final은 불변성 추가
    private String username;
    private String password;
    private String email;

    @Builder    //id 순차 생성 (0부터)
    public Member(int id, String username, String password, String email) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
