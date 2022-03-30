package com.dlwl9y.book.springboot.web.dto;

import com.dlwl9y.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;    // @NoArgsConstructor 어노테이션은 파라미터가 없는 기본 생성자를 생성해주고, @AllArgsConstructor 어노테이션은 모든 필드 값을 파라미터로 받는 생성자를 만들어준다. 마지막으로 @RequiredArgsConstructor 어노테이션은 final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어준다.

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
