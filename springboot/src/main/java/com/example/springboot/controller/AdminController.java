package com.example.springboot.controller;

import com.example.springboot.conmmon.Result;
import com.example.springboot.controller.dto.LoginDto;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.AdminService;
import com.example.springboot.service.Imp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImp adminServiceImp;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Admin obj){
        adminServiceImp.save(obj);
        return Result.success();
    }
    //登录
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest){
        LoginDto login = adminServiceImp.login(loginRequest);

        return Result.success(login);
    }

    //修改密码
    @PutMapping("/password")
    public Result password(@RequestBody PassWordRequest request){
        adminServiceImp.changePass(request);

        return Result.success();
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody Admin obj){
        adminServiceImp.update(obj);
        return Result.success();
    }
    //通过id删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminServiceImp.deleteById(id);
        return Result.success();
    }

    //通过id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Admin obj = adminServiceImp.getById(id);
        adminServiceImp.getById(id);
        return Result.success(obj);
    }

    //查询全部数据
    @GetMapping("/list")
    public Result list() {
        List<Admin> list = adminServiceImp.list();
        return Result.success(list);
    }
    //分页查询
    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest) {

        return Result.success(adminServiceImp.page(adminPageRequest));
    }
}
