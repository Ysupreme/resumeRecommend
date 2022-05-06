package com.ysupreme.resume.exception;
import lombok.Getter;
/**
 * @program: resume
 * @description:自定义异常
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-22 23:07
 **/

@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}
