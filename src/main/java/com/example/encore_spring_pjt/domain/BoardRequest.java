package com.example.encore_spring_pjt.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class BoardRequest {
    private Integer idx;
    private String title;
    private String content;
    private String writer;
    private Boolean noticeYn;
    private Boolean secretYn;
}
