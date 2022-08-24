package yoojin.springstudyblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.management.relation.Role;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
// ORM = Object->table
@Entity //User 클래스가 MySQL에 테이블로 생성됨
//@DynamicInsert  //Insert시 null인 필드 제외
public class User {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 전략을 따라감.
    private int id; //auto_increment

    @Column(nullable = false, length=30, unique = true)
    private String username; //아이디

    @Column(nullable = false, length=100)  //hash 암호화 해야해서 넉넉하게.
    private String password;

    @Column(nullable = false, length=50)
    private String email;

    //@ColumnDefault("'user'")  기본값이 'user'
    //private String role; //admin, user, manager.  원래는 Enum 써야함.

    //String은 실수 가능성이 있으므로 Enum을 사용
    @Enumerated(EnumType.STRING)    //DB에는 String으로 들어간다고 명시
    private RoleType role; //admin, user, manager.  원래는 Enum 써야함.

    @CreationTimestamp //시간 자동 입력
    private Timestamp createDate;

}
