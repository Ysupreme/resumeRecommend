package com.ysupreme.resume.controller;

import cn.hutool.core.util.StrUtil;
import com.ysupreme.resume.common.Constant;
import com.ysupreme.resume.utils.Result;
import com.ysupreme.resume.entity.Job;
import com.ysupreme.resume.exception.ServiceException;
import com.ysupreme.resume.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: resume
 * @description: the controller for job
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 11:46
 **/

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;
    @GetMapping ("/alljob")
    public Result getalljob(){
        List<Job> joblist = jobService.getalljob();
        return Result.success(joblist);
    }

    @GetMapping("/jobdetail")
    public Result getjobdetail(@RequestParam int id){
        if(id<1){
            throw new ServiceException(Constant.CODE_400,"请求参数有误");
        }else {
            Job job = jobService.getjobdetailbyid(id);
            return Result.success(job);
        }
    }

    // 分页查询
    //  接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    //limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
//    @GetMapping("/page")
//    public Result findPage(@RequestParam Integer pageNum,
//                           @RequestParam Integer pageSize,
//                           @RequestParam String experience,
//                           @RequestParam String educationlevel,
//                           @RequestParam String city) {
//        pageNum = (pageNum - 1) * pageSize;
////        //判断空
////        if(StrUtil.isBlank(experience)&&StrUtil.isBlank(city)&&StrUtil.isBlank(educationlevel)){
////            return Result.error(Constant.CODE_600,"请至少填写一个搜索项");
////        }
//
//            //因为只要三个搜索项，不是全部为空，就可以搜索，
//            //但是，如果传递的是空字符串，后面那个动态sql不会处理，因此将空字符串转换为null
//            if(StrUtil.isBlank(experience)){
//                experience = null;
//            }
//            if(StrUtil.isBlank(city)){
//                city = null;
//            }
//            if(StrUtil.isBlank(educationlevel)){
//                educationlevel = null;
//            }
////            city = "%" + city + "%";
////            experience = "%" + experience + "%";
////            educationlevel = "%" + educationlevel + "%";
//            System.out.println("city:"+city);
//            System.out.println("experience:"+experience);
//            System.out.println("educationlevel:"+educationlevel);
//
//            List<Job> data = jobService.getjobbypage(pageNum, pageSize, experience,city,educationlevel);
//            Integer total = jobService.selectTotal(experience,city,educationlevel);
//            Map<String, Object> res = new HashMap<>();
//            res.put("data", data);
//            res.put("total", total);
//            return Result.success(res);
//    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String experience,
                           @RequestParam String educationlevel,
                           @RequestParam String city) {
        pageNum = (pageNum - 1) * pageSize;
        if(StrUtil.isBlank(experience)){
            experience = null;
        }
        if(StrUtil.isBlank(city)){
            city = null;
        }
        if(StrUtil.isBlank(educationlevel)){
            educationlevel = null;
        }
        List<Job> data = jobService.getjobbypage(pageNum, pageSize, experience,city,educationlevel);
        Integer total = jobService.selectTotal(experience,city,educationlevel);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return Result.success(res);
    }


}
