package com.example.springboot.service.Imp;


import com.example.springboot.controller.dto.LoginDto;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.AdminService;
import com.example.springboot.utlis.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    //    查询全部数据
    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }
    //  分页查询
    @Override
    public Object page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(), adminPageRequest.getPageSize());
        List<Admin> users = adminMapper.listByCondition(adminPageRequest);
        return new PageInfo<>(users);
    }
    //新增
    @Override
    public void save(Admin obj) {
        adminMapper.save(obj);
    }
    //通过id查询
    @Override
    public Admin getById(Integer id) {
            return adminMapper.getById(id);
    }

    @Override
    public void update(Admin obj) {
        adminMapper.updateById(obj);
    }
    //通过id删除
    @Override
    public void deleteById(Integer id) {
        adminMapper.delete(id);
    }
    //登录
    @Override
    public LoginDto login(LoginRequest loginRequest) {
        Admin admin = adminMapper.getByUsernameAndPassWord(loginRequest.getUsername(),loginRequest.getPassword());
        if (admin == null) {
            throw new ServiceException("用户名或密码错误");
        }
        LoginDto loginDto =new LoginDto();
        BeanUtils.copyProperties(admin, loginDto);

        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());

        loginDto.setToken(token);
        return loginDto;
    }

    @Override
    public void changePass(PassWordRequest request) {

        int count = adminMapper.updatePassword(request);
        if (count <= 0) {
            throw new ServiceException("修改失败");
        }
    }
}



    /*@Autowired
    private AdminMapper adminMapper;
//    查询全部数据
    @Override
    public List<User> list() {
        return adminMapper.list();

    }
    //  分页查询
    @Override
    public Object page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<User> users = adminMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }


    //新增
    @Override
    public void save(Admin obj) {
        //当做卡号username
        //user.setUsername(IdUtil.fastSimpleUUID());
        adminMapper.save();
    }
    //通过id查询
    @Override
    public User getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin obj) {

    }

    //修改

    //通过id删除
    @Override
    public void deleteById(Integer id) {
        adminMapper.delete(id);
    }

*/