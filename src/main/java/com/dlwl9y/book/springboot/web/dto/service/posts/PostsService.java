package com.dlwl9y.book.springboot.web.dto.service.posts;

import com.dlwl9y.book.springboot.domain.posts.Posts;
import com.dlwl9y.book.springboot.domain.posts.PostsRepository;
import com.dlwl9y.book.springboot.web.dto.PostsListResponseDto;
import com.dlwl9y.book.springboot.web.dto.PostsResponseDto;
import com.dlwl9y.book.springboot.web.dto.PostsSaveRequestDto;
import com.dlwl9y.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);  // postsRepository.delete(posts): JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용한다. 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있다. 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제합니다.
    }

    @Transactional(readOnly = true)     // (readOnly = true)를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에 CRUD 기능이 없을 때 사용하면 좋다.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)     // .map(PostsListResponseDto::new) 는 .map(posts -> new PostsListResponseDto(posts))와 같다. postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 반환 -> List로 변환하는 메소드이다.
                .collect(Collectors.toList());
    }
}