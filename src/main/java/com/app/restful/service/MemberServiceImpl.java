package com.app.restful.service;

import com.app.restful.domain.MemberVO;
import com.app.restful.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    @Override
    public Optional<MemberVO> getMemberInfo(Long id) {
        return memberDAO.findById(id);
    }
}
