package com.ysupreme.resume.service;

import com.ysupreme.resume.common.Constant;
import com.ysupreme.resume.entity.Job;
import com.ysupreme.resume.entity.Resume;
import com.ysupreme.resume.exception.ServiceException;
import com.ysupreme.resume.mapper.JobMapper;
import com.ysupreme.resume.mapper.ResumeMapper;
import com.ysupreme.resume.utils.RecommendClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 22:42
 **/
@Component
public class ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;
    @Autowired
    private JobMapper jobMapper;


    public List<Resume> getallresume() {
        return resumeMapper.getallresume();
    }

    public List<Resume> getresumebypage(Integer pageNum, Integer pageSize, String experience, String expectcity, String educationlevel,String userid) {
        List<Resume> resumeList = resumeMapper.selectPage(pageNum,pageSize,experience,expectcity,educationlevel,userid);
        if(resumeList==null){
            throw new ServiceException(Constant.CODE_600,"未查找到数据项");
        }else {
            return resumeList;
        }

    }

    public Integer selectTotal(String experience, String expectcity, String educationlevel,String userid) {
        Integer total = resumeMapper.selectTotal(experience,expectcity,educationlevel,userid);
        if(total==0){
            throw new ServiceException(Constant.CODE_500,"未查找到数据项");
        }
        return  total;
    }

    public Resume getresumedetail(int id) {
        Resume myresume = resumeMapper.getresumedetailbyid(id);
        if(myresume==null){
            throw new ServiceException(Constant.CODE_500,"未查找到简历数据");
        }else {
            return myresume;
        }
    }

    public List<Resume> getrecommendresume(int id) {
        //resumeList实际就是总共的简历
        List<Resume> resumeList = resumeMapper.getrecommendresume();
        TreeMap<Integer,Float> normalmap =null;

        if(resumeList==null){
            throw new ServiceException(Constant.CODE_600,"简历库中无简历");
        }else {
            //取出对应的岗位信息
            Job job = jobMapper.getjobdetailbyid(id);

            //传入job,resumeList对取回来的简历，进行处理
            normalmap = RecommendClass.recommend(job,resumeList);
        }
        //思考返回什么
        //normalmap是一个treemap
        System.out.println("遍历一下normalmap：");

//        //遍历一下
//        Set<Integer> key = normalmap.keySet();
//        Collection<Float> value = normalmap.values();
//        //映射集合
//        Set<Map.Entry<Integer,Float>> entries = normalmap.entrySet();
//        System.out.println(key);
//        System.out.println(value);
//        System.out.println(entries);

        //对treemap按照value排序
        normalmap = RecommendClass.treemapsortbyvalue(normalmap);
        return resumeList;
    }

    //treemap根据value进行排序


    //根据用户id，返回简历列表
    public List<Resume> getownerresume(String userid){
        List<Resume> list = resumeMapper.getownerresume(userid);
        if(list!=null){
            return list;
        }else {
            throw new ServiceException(Constant.CODE_400,"服务器内部错误");
        }
    }

    public Integer upholdresume(Resume resume) {
        Integer re = resumeMapper.upholdresume(resume);
        if(re==1){
            return re;
        }else {
            throw new ServiceException(Constant.CODE_500,"后台服务器出错");
        }
    }
}
