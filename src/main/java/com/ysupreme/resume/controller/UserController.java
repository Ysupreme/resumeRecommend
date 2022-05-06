package com.ysupreme.resume.controller;

import cn.hutool.core.util.StrUtil;
import com.ysupreme.resume.utils.Result;
import com.ysupreme.resume.entity.User;
import com.ysupreme.resume.mapper.UserMapper;
import com.ysupreme.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: resume
 * @description: for user
 * @author: HuangYong
 * @github:https://github.com/Ysupreme
 * @create: 2022-04-07 23:17
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @GetMapping("/alluser")
    public List<User> index() {
        return userMapper.findALL();
    }

    @PostMapping("/insert")
    //这个@RequestBody注解，可以将前端的json数据映射成一个user对象
    public Integer save(@RequestBody User user){
        int re = userMapper.insert(user);
        return re;
    }

    //通过id返回用户信息
    @GetMapping("userinfo")
    public User getuserinfor(String userid) {
        return userMapper.getuserinfobyid(userid);
    }
    // 分页查询
    //  接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    //limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username) {
        pageNum = (pageNum - 1) * pageSize;
        username = "%" + username + "%";
        System.out.println("username="+username);
        List<User> data = userMapper.selectPage(pageNum, pageSize, username);
        Integer total = userMapper.selectTotal(username);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    //暂定废除
    //对用户信息进行修改
    // 新增和修改
    @PostMapping("/save")
    public Result saveplus(@RequestBody User user) {
        // 新增或者更新
        if(userService.save(user)==1){
            return Result.success();
        }else {
            return Result.error();
        }
    }
    //注册
    @PostMapping("/register")
    public Result register(@RequestBody User user){
//        靠前端处理两次密码是否一致
        if(StrUtil.isBlank(user.getUsername())||StrUtil.isBlank(user.getUserid())||StrUtil.isBlank(user.getPasswd())){
            return Result.error("400","参数错误");
        }else{
            User myuser = userService.register(user);
            return Result.success(myuser);
        }
    }

    @PostMapping("/login")
    //暂时考虑返回integer
    public Result login(@RequestBody User user){
        String userid = user.getUserid();
        String passwd = user.getPasswd();
        //使用工具类，判断参数是否为空
        if(StrUtil.isBlank(userid)||StrUtil.isBlank(passwd)){
            return Result.error("400","参数错误");
        }else{
            User myuser = userService.login(user);
            return Result.success(myuser);
        }
    }

    @PostMapping("/modify")
    //暂时考虑返回integer
    //应该需要使用到mybaits的动态sql
    public Integer modify(@RequestBody User user){
        // 只能修改，若想要新增，调用注册接口
        return userService.save(user);
    }

    //用户注销
    /*
     * @Param
     * @return
     *  0:无删除
     *  1：对应id的用户被删除
     */
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable String id){
        return userService.deletebyid(id);
    }
}
