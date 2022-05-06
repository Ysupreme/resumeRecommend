package com.ysupreme.resume;

import com.ysupreme.resume.entity.User;
import com.ysupreme.resume.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
public class ResumeApplication {


    public static void main(String[] args) {
        SpringApplication.run(ResumeApplication.class, args);
    }



}
