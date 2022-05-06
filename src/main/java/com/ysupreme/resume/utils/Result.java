package com.ysupreme.resume.utils;

import com.ysupreme.resume.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: resume
 * @description: 返回结果的统一类
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-22 21:24
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /*
     * @Author huangyong
     * @Description
     * @Date 21:25 2022/4/22
     * @Param
     * @return
     *  见Constant接口约定
     **/
    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(Constant.CODE_200,"",null);
    }
    public static Result success(Object data){
        return new Result(Constant.CODE_200,"",data);
    }
    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }
    //默认系统错误
    public static Result error(){
        return new Result(Constant.CODE_500,"系统错误",null);
    }

}
