package com.ysupreme.resume.utils;

import com.ysupreme.resume.common.Constant;
import com.ysupreme.resume.entity.Job;
import com.ysupreme.resume.entity.Resume;

import java.util.*;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-24 15:50
 **/

public class RecommendClass {

    //用两个map来存储推荐值  or  3个
    //一个是normalmap 一个是abnormalmap 另一个是totalrecmap


    /*
     * @Author huangyong
     * @Description
     * @Date 16:07 2022/4/24
     * @Param
     * @return
     *
     * 1）educationlevel(学历)---权重：0.2
        2）city---expectcity ---权重：0.1
        3)age--age---权重：0.2
        4)experience（工作经验）---权重：0.1
        * 权重累计：0.6
        *
       再通过非规格化的要求与介绍比较，得出推荐值：---权重：0.4
     **/
    public static TreeMap<Integer, Float> recommend(Job job, List<Resume> resumeList){
        //需要返回一个推荐值
        //返回的简历数Max=30
        //需要记录id--以及相对于的推荐系数
        TreeMap<Integer,Float> agemap = RecommendClass.ageRecommend(job,resumeList);
        TreeMap<Integer,Float> edumap = RecommendClass.eduRecommend(job,resumeList);
        TreeMap<Integer,Float> citymap = RecommendClass.cityRecommend(job,resumeList);
        TreeMap<Integer,Float> exmap = RecommendClass.exRecommend(job,resumeList);
        TreeMap<Integer,Float> normalmap = new TreeMap<>();
        int length = agemap.size();
        for(int i = 0; i<length; i++){
            if(agemap.containsKey(i)&&edumap.containsKey(i)&&citymap.containsKey(i)&&exmap.containsKey(i)){
                Float value = agemap.get(i)* Constant.ageWeight +edumap.get(i)*Constant.eduWeight
                        +citymap.get(i)*Constant.cityWeight+exmap.get(i)*Constant.exWeight;
                normalmap.put(i,value);
            }
        }
        return normalmap;
    }


    //城市权重为0.1
    public static TreeMap<Integer, Float> cityRecommend(Job job, List<Resume> resumeList){
        TreeMap<Integer, Float> normalmap = new TreeMap<>();
        for(Resume singleresume : resumeList){
            String resumecity = singleresume.getExpectcity();
            String jobcity = job.getCity();
//            System.out.println("cityRecommend"+resumecity+"---"+jobcity);
            if(resumecity.equals(jobcity)){
                normalmap.put(Integer.valueOf(singleresume.getId()),1f);
            }else {
                normalmap.put(Integer.valueOf(singleresume.getId()),0f);
            }
        }
        return normalmap;
    }

    //学历权重0.2
    public static TreeMap<Integer, Float> eduRecommend(Job job, List<Resume> resumeList){
        TreeMap<Integer, Float> normalmap = new TreeMap<>();
        for(Resume singleresume : resumeList){
            String resumeedu = singleresume.getEducationlevel();
            String jobedu = job.getEducationlevel();
//            System.out.println("eduRecommend"+resumeedu+"---"+jobedu);

            //比较
            if("学历不限".equals(jobedu))
            {
                normalmap.put(Integer.valueOf(singleresume.getId()),1f);
            }else if("硕士及以上".equals(jobedu)){
                if("2".equals(resumeedu)){
                    normalmap.put(Integer.valueOf(singleresume.getId()),1f);
                }else {
                    normalmap.put(Integer.valueOf(singleresume.getId()),0f);
                }
            }else if("本科及以上".equals(jobedu)){
                if("1".equals(resumeedu)){
                    normalmap.put(Integer.valueOf(singleresume.getId()),1f);
                }else if("2".equals(resumeedu)){
                    normalmap.put(Integer.valueOf(singleresume.getId()),1.5f);
                }else {
                    normalmap.put(Integer.valueOf(singleresume.getId()),0f);
                }
            }
        }
        return normalmap;
    }

    //年龄权重为0.2
    public static TreeMap<Integer, Float> ageRecommend(Job job, List<Resume> resumeList){
        TreeMap<Integer, Float> normalmap = new TreeMap<>();
        for(Resume singleresume : resumeList){
            String resumeage = singleresume.getAge();
            String jobage = job.getAge();
//            System.out.println("ageRecommend"+resumeage+"---"+jobage);

            int resumeages = Integer.valueOf(resumeage);
            //比较
            if("年龄不限".equals(jobage)){
                normalmap.put(Integer.valueOf(singleresume.getId()),1f);
            }else if("35岁以下".equals(jobage)){
                if(resumeages<35){
                    normalmap.put(Integer.valueOf(singleresume.getId()),1f);
                }else {
                    normalmap.put(Integer.valueOf(singleresume.getId()),0f);
                }
            }
        }
        return normalmap;
    }
    //工作经验权重为0.1
    public static TreeMap<Integer, Float> exRecommend(Job job, List<Resume> resumeList){
        TreeMap<Integer, Float> normalmap = new TreeMap<>();
        for(Resume singleresume : resumeList){
            String resumeex = singleresume.getExperience();
            String jobex = job.getExperience();
//            System.out.println("exRecommend"+resumeex+"---"+jobex);

            int resumeexs = Integer.valueOf(resumeex);
            //比较
            if("经验不限".equals(jobex)){
                normalmap.put(Integer.valueOf(singleresume.getId()),1f);
            }else if("1-3年".equals(jobex)){
                if(resumeexs<1){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.4f);
                }else {
                    normalmap.put(Integer.valueOf(singleresume.getId()),1f);
                }
            }else if("3-5年".equals(jobex)){
                if(resumeexs<1){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.3f);
                }else if(resumeexs>=1&&resumeexs<3){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.8f);
                }else {
                    normalmap.put(Integer.valueOf(singleresume.getId()),1f);
                }
            }else if("5-10年".equals(jobex)){
                if(resumeexs<1){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.2f);
                }else if(resumeexs>=1&&resumeexs<3){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.6f);
                }else if(resumeexs>=3&&resumeexs<5){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.8f);
                }else {
                    normalmap.put(Integer.valueOf(singleresume.getId()),1f);
                }
            }else if("10年以上".equals(jobex)){
                if(resumeexs<1){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.1f);
                }else if(resumeexs>=1&&resumeexs<3){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.2f);
                }else if(resumeexs>=3&&resumeexs<5){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.6f);
                }else if(resumeexs>=5&&resumeexs<10){
                    normalmap.put(Integer.valueOf(singleresume.getId()),0.8f);
                }else {
                    normalmap.put(Integer.valueOf(singleresume.getId()),1f);
                }
            }
        }
        return normalmap;
    }

    public static TreeMap<Integer, Float>  treemapsortbyvalue(TreeMap<Integer, Float> map){
        TreeMap<Integer, Float> treeResult = new TreeMap<Integer, Float>(new ValueComparator(map));
        treeResult.putAll(map);
        return  treeResult;
    }

}
