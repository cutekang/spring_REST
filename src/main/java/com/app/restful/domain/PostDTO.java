package com.app.restful.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PostDTO {
    private Long id;
    private String postTitle;
    private String postContent;
    private Long memberId;
    private Long postReadCount;
    private String memberName;
}
