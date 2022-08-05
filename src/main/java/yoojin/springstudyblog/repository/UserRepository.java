package yoojin.springstudyblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yoojin.springstudyblog.model.User;

//PK가 Integer인 User 테이블이 관리하는 저장소
// @Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
