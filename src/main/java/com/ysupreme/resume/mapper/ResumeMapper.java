package com.ysupreme.resume.mapper;

import com.ysupreme.resume.entity.Resume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 12:11
 **/
@Mapper
public interface ResumeMapper {

    @Select("select  * from resume ")
    List<Resume> getallresume();

    List<Resume> selectPage(Integer pageNum, Integer pageSize, String experience, String expectcity, String educationlevel,String userid);
    Integer selectTotal( String experience, String expectcity, String educationlevel,String userid);

    @Select("select * from  resume where id = #{id}")
    Resume getresumedetailbyid(int id);

    //先取出所有的简历，传到service层，进行处理
    @Select("select * from resume")
    List<Resume> getrecommendresume();

    @Select("select * from  resume where userId = #{userid}")
    List<Resume> getownerresume(String userid);

    @Insert("insert into resume(userName,sex,educationLevel,expectCity,telephone,age,expectSalary,introduction,major,experience,userId)" +
            "values(#{username},#{sex},#{educationlevel},#{expectcity},#{telephone},#{age},#{expectsalary}," +
            "#{introduction},#{major},#{experience},#{userid})")
    Integer upholdresume(Resume resume);
}
