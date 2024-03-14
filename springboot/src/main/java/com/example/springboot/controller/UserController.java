package com.example.springboot.controller;

import com.example.springboot.conmmon.Result;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.service.Imp.UserServiceImp;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImp userServiceImp;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userServiceImp.save(user);
        return Result.success();
    }

    @PostMapping("/account")
    public Result account(@RequestBody User user){
        userServiceImp.handleAccount(user);
        return Result.success();
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userServiceImp.update(user);
        return Result.success();
    }
    //通过id删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userServiceImp.deleteById(id);
        return Result.success();
    }

    //通过id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = userServiceImp.getById(id);
        return Result.success(user);
    }

    //查询全部数据
    @GetMapping("/list")
    public Result list() {
        List<User> userList = userServiceImp.list();
        return Result.success(userList);
    }
    //分页查询
    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest) {

        return Result.success(userServiceImp.page(userPageRequest));
    }
}
