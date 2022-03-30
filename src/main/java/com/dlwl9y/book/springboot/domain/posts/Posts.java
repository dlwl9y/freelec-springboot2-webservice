package com.dlwl9y.book.springboot.domain.posts;

import com.dlwl9y.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter     // (롬복 라이브러리 어노테이션) 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor      // (롬복 라이브러리 어노테이션) 기본생성자 자동 추가. public Posts(){}와 같은 효과
@Entity     // (JPA 어노테이션) 테이블과 링크될 클래스임을 나타낸다. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다. ex) SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {    // Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이며, 보통 Entity 클래스라고 한다. JPA를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다는, 이 Entitiy 클래스의 수정을 통해 작업한다.

    @Id     // (JPA 어노테이션) 해당 테이블의 PK 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // @GeneratedValue: (JPA 어노테이션) PK의 생성 규칙을 나타낸다. 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false)     // @Column: (JPA 어노테이션) 테이블의 칼럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다. 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다. 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex:title), 타입을 TEXT로 변경하고 싶거나(ex:content) 등의 경우에 사용된다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // (롬복 라이브러리 어노테이션) 해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}   // 자바빈 규약을 생각하면서 getter/setter를 무작정 생성하는 경우가 있다. 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없어, 차후 기능 변경시 정말 복잡해진다. 그래서 Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다. 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 한다.