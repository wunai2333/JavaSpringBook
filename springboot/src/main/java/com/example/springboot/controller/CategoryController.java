package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.conmmon.Result;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.CategoryRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.service.Imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Category obj){
        categoryServiceImp.save(obj);
        return Result.success();
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody Category obj){
        categoryServiceImp.update(obj);
        return Result.success();
    }
    //通过id删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        categoryServiceImp.deleteById(id);
        return Result.success();
    }

    //通过id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Category obj = categoryServiceImp.getById(id);
        categoryServiceImp.getById(id);
        return Result.success(obj);
    }

    //查询全部数据
    @GetMapping("/list")
    public Result list() {
        List<Category> list = categoryServiceImp.list();
        return Result.success(list);
    }

    @GetMapping("/tree")
    public Result tree() {
        List<Category> list = categoryServiceImp.list();

        // 对list操作
    //    List<Category> treeList = list.stream().filter(v -> v.getPid() == null).collect(Collectors.toList());//第一层



        return Result.success(createTree(null, list));
    }
    //完全递归的办法来实现递归树
    private List<Category> createTree(Integer pid, List<Category> categories) {
        List<Category> treeList = new ArrayList<>();
        for (Category category : categories) {
            if (pid == null) {
                if (category.getPid() == null) {//这是第一层
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }else {
                if (pid.equals(category.getPid())) {
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
                if (CollUtil.isEmpty(category.getChildren())) {
                    category.setChildren(null);
                }
            }
        }
        return treeList;
    }

    //分页查询
    @GetMapping("/page")
    public Result page(CategoryRequest categoryRequest) {

        return Result.success(categoryServiceImp.page(categoryRequest));
    }
}
