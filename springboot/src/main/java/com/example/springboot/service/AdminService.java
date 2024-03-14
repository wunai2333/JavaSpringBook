package com.example.springboot.service;

import com.example.springboot.controller.dto.LoginDto;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.entity.Admin;

import java.util.List;

public interface AdminService {
    
    List<Admin> list();

    Object page(AdminPageRequest adminPageRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin obj);

    void deleteById(Integer id);

    LoginDto login(LoginRequest loginRequest);

    void changePass(PassWordRequest request);
}
