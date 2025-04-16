package com.app.restful.service;

import com.app.restful.domain.PostDTO;
import com.app.restful.domain.PostVO;
import com.app.restful.repository.PostDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    @Override
    public List<PostDTO> getPosts() {
        return postDAO.findAll();
    }

    @Override
    public Optional<PostDTO> getPost(Long id) {
        return postDAO.findById(id);
    }

    //    게시글 작성
    @Override
    public void write(PostVO postVO){
        postDAO.save(postVO);
    }

    //    게시글 수정
    @Override
    public void modify(PostVO postVO){
        postDAO.update(postVO);
    }

    //    게시글 삭제
    @Override
    public void remove(Long id){
        postDAO.delete(id);
    }
}
