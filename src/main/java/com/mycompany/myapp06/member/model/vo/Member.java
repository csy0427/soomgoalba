package com.mycompany.myapp06.member.model.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class Member {
    private static final long serialVersionUID = 1111L; 
    private String id; 
    private String passwd; 
    private String name; 
    private String email; 
    private String gender; 
    private int age; 
    private String phone; 
    private String address; 
    private Date enrollDate;
}
