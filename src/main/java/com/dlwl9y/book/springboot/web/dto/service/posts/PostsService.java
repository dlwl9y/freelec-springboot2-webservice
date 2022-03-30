package com.dlwl9y.book.springboot.web.dto.service.posts;

import com.dlwl9y.book.springboot.domain.posts.Posts;
import com.dlwl9y.book.springboot.domain.posts.PostsRepository;
import com.dlwl9y.book.springboot.web.dto.PostsResponseDto;
import com.dlwl9y.book.springboot.web.dto.PostsSaveRequestDto;
import com.dlwl9y.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor    // final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성한다.
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}