package com.ysupreme.resume.controller;

import com.ysupreme.resume.utils.Result;
import com.ysupreme.resume.entity.Company;
import com.ysupreme.resume.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: resume
 * @description:
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-23 21:52
 **/
@RequestMapping("/company")
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @GetMapping("/getcompanyinfo")
    public Result getcompanyinfobyid(@RequestParam int id){
        //检查id
        Company mycompany = companyService.getcompanyinfobyid(id);
        return Result.success(mycompany);
    }

}
