package com.ysupreme.resume.mapper;

import com.ysupreme.resume.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 12:11
 **/
@Mapper
public interface CompanyMapper {

    @Select("select * from company where companyId = #{id}")
    Company getcompanyinfobyid(int id);
}
