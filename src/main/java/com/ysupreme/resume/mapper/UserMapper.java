package com.ysupreme.resume.mapper;

import com.ysupreme.resume.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //以后改进使用动态代理，暂时不用

    //1.返回所有用户对象--一个List
    @Select("SELECT * from userinfo")
    List<User> findALL();

    //2.根据id（只有一个string参数），返回User对象
    @Select("SELECT * from userinfo where userId=#{userid}")
    User getuserinfobyid(String userid);

    //3.通过user对象查询有无用户记录，返回id
    @Select("SELECT userId from userinfo where userId = #{userid}")
    String haveuserid(User user);

    //4.通过Id查询有无用户记录,返回id
    @Select("SELECT userId from userinfo where userId = #{userid}")
    String haveuseridbyid(String userid);

    //5.参数为User user，插入一条用户记录，返回int
    @Insert("insert into userinfo(userName,userId,passWd,permission,userType) VALUES(#{username},#{userid}," +
            "#{passwd},#{permission},#{usertype})")
    int insert(User user);

    //自己写的分页查询
    @Select("select * from userinfo where userName like #{username} limit #{pageNum}, #{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username);

//    @Select("select count(*) from userinfo where username like concat('%', #{username}, '%') ")
    @Select("select count(*) from userinfo where userName like #{username}")
    Integer selectTotal(String username);

    int update(User user);

    @Delete("delete from userinfo where userId = #{id}")
    Integer deletebyid(@Param("id") String id);
}
