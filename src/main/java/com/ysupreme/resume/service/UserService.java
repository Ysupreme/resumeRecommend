package com.ysupreme.resume.service;

import com.ysupreme.resume.common.Constant;
import com.ysupreme.resume.entity.User;
import com.ysupreme.resume.exception.ServiceException;
import com.ysupreme.resume.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-08 00:47
 **/
@Component
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //根据id，返回null或者user对象
    public User getuserinfo(String userid){
        if(userMapper.haveuseridbyid(userid)==null){
            return null;
        }else {
            return userMapper.getuserinfobyid(userid);
        }
    }
    /*
     * @Author huangyong
     * @Description
     * @Date 13:35 2022/4/8
     * @Param
     * @return -- flag
     *  1:注册成功
     *  -1：注册失败
     **/
    public User register(User user){
        //判断空
        User myuser;
        //尝试注册
        //默认将用户的权限设为0，普通权限
        user.setPermission("0");
        //说明该userid存在于数据库中，不予注册
        if(userMapper.haveuserid(user)!=null){
            throw new ServiceException(Constant.CODE_600,"该用户已存在");
        }else {
            userMapper.insert(user);
            return user;
        }
    }

    //分页查询
    public int findpage(User user){
        int flag = 0;
        if(user.getUserid()!=null){
            //判断是否有记录
            if(userMapper.haveuserid(user)!=null){
                return flag;
            }else {
                userMapper.insert(user);
                flag = 1;
            }
        }
        return flag;
    }

    //修改用户信息
    public int save(User user) {
        int flag = -1;
        if(userMapper.getuserinfobyid(user.getUserid())==null){
            throw  new ServiceException(Constant.CODE_400,"参数错误");
        }else {
            //否则更新
            return userMapper.update(user);
        }
    }

    public Integer deletebyid(String id) {
        return userMapper.deletebyid(id);
    }

    public User login(User user) {
        User myuser = userMapper.getuserinfobyid(user.getUserid());
            //若用户不存在或者密码不正确
            if(myuser!=null&&myuser.getPasswd().equals(user.getPasswd())){
                return myuser;
            }else {
                //异常
                throw new ServiceException(Constant.CODE_600,"用户名或密码错误");
            }
    }
}

