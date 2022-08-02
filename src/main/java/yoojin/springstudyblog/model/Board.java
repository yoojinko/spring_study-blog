package yoojin.springstudyblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞인 string

    @ColumnDefault("0") //string이면 안에 따옴표 써야함. 숫자는 아님
    private int count; //조회수

    @ManyToOne              //보드가 many, one이 user
    @JoinColumn(name="userId")
    private User user;  //DB는 오브젝트를 저장할 수 없어 FK를 사용. 자바는 오브젝트를 저장할 수 있음.
                        //=> 자바에서 DB에 맞춰 테이블을 만듦. (User의 PK로 자동 변환)

    @OneToMany(mappedBy="board", fetch=FetchType.EAGER)// reply table의 board와 매핑. mappedBy -> 연관관계의 주인이 아님. (DB에 Column을 만들지 않음)
                                                        // 기본 fetch는 LAZY이나, 게시글에서 댓글 정보를 바로 받아와야 하므로 EAGER 전략으로 변경
    private List<Reply> reply;  // 따라서 JoinColumn은 필요없다.

    @CreationTimestamp
    private Timestamp createDate;
}
