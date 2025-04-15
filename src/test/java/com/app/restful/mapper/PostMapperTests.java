package com.app.restful.mapper;

import com.app.restful.domain.PostDTO;
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
}
