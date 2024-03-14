package com.example.springboot.service.Imp;


import cn.hutool.core.util.IdUtil;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;
//    查询全部数据
    @Override
    public List<User> list() {
        return userMapper.list();

    }
//  分页查询
    @Override
    public Object page(UserPageRequest userPageRequest) {
        PageHelper.startPage(userPageRequest.getPageNum(), userPageRequest.getPageSize());
        List<User> users = userMapper.listByCondition(userPageRequest);
        return new PageInfo<>(users);
    }
    //新增
    @Override
    public void save(User user) {
        //当做卡号username
        //user.setUsername(IdUtil.fastSimpleUUID());
        userMapper.save(user);
    }
    //通过id查询
    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }
    //修改
    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }
    //通过id删除
    @Override
    public void deleteById(Integer id) {
        userMapper.delete(id);
    }

    @Override
    public void handleAccount(User user) {
        Integer score = user.getScore();
        if (score  == null) {
            return;
        }
        Integer id = user.getId();
        User byId = userMapper.getById(id);
        byId.setAccount(byId.getAccount() + score);
        userMapper.updateById(byId);
    }


}
