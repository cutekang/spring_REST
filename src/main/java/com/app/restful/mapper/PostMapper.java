package com.app.restful.mapper;

import com.app.restful.domain.PostDTO;
import com.app.restful.domain.PostVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

//    게시글 목록
    public List<PostDTO> selectAll();
    
//    게시글 단일 조회
    public Optional<PostDTO> selectById(Long id);
}
