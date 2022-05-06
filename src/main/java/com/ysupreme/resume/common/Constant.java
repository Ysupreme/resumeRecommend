package com.ysupreme.resume.common;

public interface Constant {

    //返回码
    String CODE_200 = "200"; //成功
    String CODE_401 = "401";  // 权限不足
    String CODE_400 = "400";  // 参数错误
    String CODE_500 = "500"; // 系统错误
    String CODE_600 = "600"; // 其他业务异常

    //用户类型
    String TYPE_RESUME = "1";   //求职人员
    String TYPE_COMPANY = "2";  //招聘者
    String TYPE_SYSTEM = "3";   //系统管理者

    //权重
    float ageWeight = 0.2f;
    float exWeight = 0.1f;
    float cityWeight = 0.1f;
    float eduWeight = 0.2f;
    float dutyWeight = 0.4f;
}
