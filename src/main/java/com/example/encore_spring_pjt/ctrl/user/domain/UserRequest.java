package com.example.encore_spring_pjt.ctrl.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserRequest {
    private String id;
    private String pwd;
}
