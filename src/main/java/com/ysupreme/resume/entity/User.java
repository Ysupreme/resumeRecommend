package com.ysupreme.resume.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;

/**
 * @program: resume
 * @description: the user of the system
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-07 19:28
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;    //用户名
    private String passwd;      //密码
    private String userid;      //用户id
    private String permission;  //用户权限
    private String usertype;    //用户类型--1.求职者   2.招聘人员  3.管理员
    private String createTime;  //账号创建时间
}
