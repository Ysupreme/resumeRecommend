package com.ysupreme.resume.mapper;

import com.ysupreme.resume.entity.Job;
import com.ysupreme.resume.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 12:11
 **/
@Mapper
public interface JobMapper {


    //1.返回所有岗位--一个List
    @Select("SELECT * from job")
    List<Job> findALL();

    //2.根据id返回岗位
    @Select("SELECT * from job where id =#{id}")
    Job getjobdetailbyid(int id);

    //自己写的分页查询
//    @Select("select * from job where city like #{city} limit #{pageNum}, #{pageSize}")
    List<Job> selectPage(Integer pageNum, Integer pageSize,String experience,String city,String educationlevel);

//    @Select("select count(*) from job where userName like #{username}")
    Integer selectTotal(String experience, String city, String educationlevel);


}
