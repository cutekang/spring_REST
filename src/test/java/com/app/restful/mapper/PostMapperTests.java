package com.app.restful.mapper;

import com.app.restful.domain.PostDTO;
import com.app.restful.domain.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class PostMapperTests {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void selectAllTest() {
        postMapper.selectAll().stream().map(PostDTO::toString).forEach(log::info);
    }

    @Test
    public void selectByIdTest() {
        log.info(postMapper.selectById(1L).toString());
    }

    @Test
    public void insertPostTest(){
        PostVO postVO = new PostVO();

        postVO.setPostTitle("테스트 테스트 테스트");
        postVO.setPostContent("응 테스트");
        postVO.setMemberId(1L);

        postMapper.insert(postVO);
    }

    @Test
    public void updatePostTest(){
        PostVO postVO = new PostVO();

        postVO.setId(10L);
        postVO.setPostTitle("업데이트 테스트 테스트");
        postVO.setPostContent("수정수정");
        postVO.setMemberId(1L);

        postMapper.update(postVO);
    }
}
