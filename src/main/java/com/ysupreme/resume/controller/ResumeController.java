package com.ysupreme.resume.controller;

import cn.hutool.core.util.StrUtil;
import com.ysupreme.resume.common.Constant;
import com.ysupreme.resume.utils.Result;
import com.ysupreme.resume.entity.Resume;
import com.ysupreme.resume.exception.ServiceException;
import com.ysupreme.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 22:37
 **/
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    @GetMapping("/allresume")
    public Result getallresume(){
        List<Resume> resumelist = resumeService.getallresume();
        return Result.success(resumelist);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String experience,
                           @RequestParam String educationlevel,
                           @RequestParam String expectcity,
                           @RequestParam String userid) {
        pageNum = (pageNum - 1) * pageSize;
        if(StrUtil.isBlank(experience)){
            experience = null;
        }
        if(StrUtil.isBlank(expectcity)){
            expectcity = null;
        }
        if(StrUtil.isBlank(educationlevel)){
            educationlevel = null;
        }
        if(StrUtil.isBlank(userid)){
            userid = null;
        }
//            city = "%" + city + "%";
//            experience = "%" + experience + "%";
//            educationlevel = "%" + educationlevel + "%";
        System.out.println("city:"+expectcity);
        System.out.println("experience:"+experience);
        System.out.println("educationlevel:"+educationlevel);
        List<Resume> data = resumeService.getresumebypage(pageNum, pageSize, experience,expectcity,educationlevel,userid);
        Integer total = resumeService.selectTotal(experience,expectcity,educationlevel,userid);
        System.out.println("?????????????????????"+total);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return Result.success(res);
    }

    //????????????
    @GetMapping("/resumedetail")
    public Result getresumedetail(@RequestParam int id){
        if(id<1){
            throw new ServiceException(Constant.CODE_400,"??????????????????");
        }else {
            Resume myresume = resumeService.getresumedetail(id);
            return Result.success(myresume);
        }
    }

    //?????????????????????
    @GetMapping("/recommend")
    public Result recommendresume(@RequestParam int id){
        if(id<1){
            throw new ServiceException(Constant.CODE_400,"?????????????????????");
        }else {
            List<Resume> resumeList = resumeService.getrecommendresume(id);
            return Result.success(resumeList);
        }
    }
    //?????????????????????
    @GetMapping("/owner")
    public Result getownerresume(@RequestParam String userid){
        if(StrUtil.isBlank(userid)){
            return Result.error(Constant.CODE_400,"????????????");
        }else {
            List<Resume> resumeList = resumeService.getownerresume(userid);
            return Result.success(resumeList);
        }
    }

    //????????????
    @PostMapping("/insert")
    public Result upholdresume(@RequestBody Resume resume){
            Integer re = resumeService.upholdresume(resume);
            return Result.success();

    }


}
