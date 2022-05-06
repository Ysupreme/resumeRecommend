package com.ysupreme.resume.service;

import com.ysupreme.resume.common.Constant;
import com.ysupreme.resume.entity.Company;
import com.ysupreme.resume.exception.ServiceException;
import com.ysupreme.resume.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 21:55
 **/
@Component
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    public Company getcompanyinfobyid(int id){
        Company myconpany = companyMapper.getcompanyinfobyid(id);
        if(null!=myconpany){
            return companyMapper.getcompanyinfobyid(id);
        }else {
            throw  new ServiceException(Constant.CODE_600,"查找出错,请检查搜索参数");
        }

    }

}
