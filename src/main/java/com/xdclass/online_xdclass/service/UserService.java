package com.xdclass.online_xdclass.service;

import com.xdclass.online_xdclass.model.entity.User;

import java.util.Map;

public interface UserService {
    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    int save(Map<String,String> userInfo);

    User findByPhone(String phone);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(int userId);
}
