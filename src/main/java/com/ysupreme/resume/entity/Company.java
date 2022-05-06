package com.ysupreme.resume.entity;

import lombok.Data;

/**
 * @program: resume
 * @description: the entity class for company
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 11:48
 **/
@Data
public class Company {
    private int companyid;
    private String companyname;
    private String size;
    private String url;
    private String registertime;
    private String registerfund;
    private String businessscope;

}
