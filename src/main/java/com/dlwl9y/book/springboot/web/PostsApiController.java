package com.dlwl9y.book.springboot.web;

import com.dlwl9y.book.springboot.web.dto.PostsResponseDto;
import com.dlwl9y.book.springboot.web.dto.PostsUpdateRequestDto;
import com.dlwl9y.book.springboot.web.dto.service.posts.PostsService;
import com.dlwl9y.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")   //등록 기능
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("api/v1/posts/{id}")    //수정 기능
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("api/v1/posts/{id}")    //조회 기능
    public PostsResponseDto findById (@PathVariable long id) {
        return postsService.findById(id);
    }
}