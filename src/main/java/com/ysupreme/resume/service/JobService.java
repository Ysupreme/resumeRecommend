package com.ysupreme.resume.service;

import com.ysupreme.resume.common.Constant;
import com.ysupreme.resume.entity.Job;
import com.ysupreme.resume.exception.ServiceException;
import com.ysupreme.resume.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 12:07
 **/
@Component
public class JobService {
    @Autowired
    private JobMapper jobMapper;

    public List<Job> getalljob(){
        return jobMapper.findALL();
    }

    public Job getjobdetailbyid(int id) {
        Job myjob = jobMapper.getjobdetailbyid(id);
        if(myjob==null){
            throw new ServiceException(Constant.CODE_500,"该岗位不存在");
        }else {
            return myjob;
        }
    }
    public List<Job> getjobbypage(Integer pageNum, Integer pageSize, String experience, String city, String educationlevel){
        //需要对三个参数进行处理
        /*
         * @Param [pageNum, pageSize, experience, age, city]
         * @return List< Job>
            age:
            educationlevel:
            city:深圳、北京、杭州、广州、成都、上海、重庆、南京、芜湖、长沙
            * age
         */
        //使用动态sql
        List<Job> myjoblist = jobMapper.selectPage(pageNum,pageSize,experience,city,educationlevel);
        if(myjoblist==null){
            throw new ServiceException(Constant.CODE_500,"未查找到数据项");
        }
        return  myjoblist;
    }


    public Integer selectTotal( String experience, String city, String educationlevel) {
        Integer total = jobMapper.selectTotal(experience,city,educationlevel);
        if(total==0){
            throw new ServiceException(Constant.CODE_500,"未查找到数据项");
        }
        return  total;
    }
}
