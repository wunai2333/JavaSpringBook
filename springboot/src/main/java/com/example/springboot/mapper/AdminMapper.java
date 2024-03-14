package com.example.springboot.mapper;

import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> list();

    List<Admin> listByCondition(AdminPageRequest adminPageRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void updateById(Admin obj);

    void delete(Integer id);

    Admin getByUsernameAndPassWord(@Param("username") String username, @Param("password") String password);

    int updatePassword(PassWordRequest request);
}
