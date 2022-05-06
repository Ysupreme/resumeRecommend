package com.ysupreme.resume.entity;

import lombok.Data;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-11 01:46
 **/

@Data
public class Resume {
    private int id;
    private String userid;  //对应的用户id

    private String username;
    private String sex;
    private String educationlevel;
    //
    private String expectcity;
    private String expectsalary;
    private String telephone;
    private String age;
    private String introduction;
    private String major;
    private String experience;

}
