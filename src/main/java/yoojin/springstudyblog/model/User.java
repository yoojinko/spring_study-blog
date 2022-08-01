package yoojin.springstudyblog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM = Object->table
@Entity //User 클래스가 MySQL에 테이블로 생성됨
public class User {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 전략을 따라감.
    private int id; //auto_increment

    @Column(nullable = false, length=30)
    private String username; //아이디

    @Column(nullable = false, length=100)  //hash 암호화 해야해서 넉넉하게.
    private String password;

    @Column(nullable = false, length=50)
    private String email;

    @ColumnDefault("'user'")
    private String role; //admin, user, manager.  원래는 Enum 써야함.

    @CreationTimestamp //시간 자동 입력
    private Timestamp createDate;

    public User() {
    }
}