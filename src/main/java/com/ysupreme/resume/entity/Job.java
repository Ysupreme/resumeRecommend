package com.ysupreme.resume.entity;

import lombok.Data;

/**
 * @program: resume
 * @description: the details of job
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-11 17:40
 **/
@Data
public class Job {
    private int id;
    private int companyid;  //对应公司的主键
    private String userid;  //对应的用户id
    private String educationlevel;
    private String location;
    private String city;
    private String age;
    private String majorrequire;    //专业要求
    private String experience;  //年数
    private String salary;
    private String duty;    //职责要求，与简历中的工作经历比较
    private String language;
}
