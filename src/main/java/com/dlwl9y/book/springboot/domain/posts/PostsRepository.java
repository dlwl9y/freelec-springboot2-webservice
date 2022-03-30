package com.dlwl9y.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {       // JpaRepository는 보통 ibatis나 MyBatis 등에서 Dao라고 불리는 DB Layer 접근자이다. JPA에선 Repository라고 부르며 인터페이스로 생성한다. 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
    /*@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();*/
}
