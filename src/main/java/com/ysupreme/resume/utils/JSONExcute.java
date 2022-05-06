package com.ysupreme.resume.utils;

import com.ysupreme.resume.entity.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-11 01:45
 **/

public class JSONExcute {
    //从给定位置读取Json文件
    public String readJson(String path){
        //从给定位置获取文件
        File file = new File(path);
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件流
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();//返回字符串
    }

    //插入方法
//    public void insert() throws IOException {
//
//        List<Resume> ResumelList = new ArrayList<>();
//        // 开始时间
//        Long begin = new Date().getTime();
//        Integer result = 0;
//        String path = "C:\\Users\\zhaohua\\Downloads\\word.json";
//        String word = readJson(path);
//        ResumelList = JSONObject.parseArray(word, Resume.class);//把字符串形式的数组转换成jsonArray 然后转换为List
//        for (Resume wordModel : ResumelList) {//增强for
//            Resume.getWord().replace(" ", "");//去除string中的空字符串
//            Resume.getOldword().replace(" ", "");
//            Resume.getStrokes().replace(" ", "");
//            Resume.getPinyin().replace(" ", "");
//            Resume.getRadicals().replace(" ", "");
//        }
//        wxdictionaryMapper.insert(wordModelList);//插入数据库
//        // 结束时间
//        Long end = new Date().getTime();
//        // 耗时
//        System.out.println("数据插入花费时间 : " + (end - begin) / 1000 + " s");
//        System.out.println("插入完成");
//    }


}
