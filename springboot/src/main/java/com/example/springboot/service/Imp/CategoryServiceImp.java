package com.example.springboot.service.Imp;

import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.CategoryRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Category;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.mapper.CategoryMapper;
import com.example.springboot.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImp implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    //    查询全部数据
    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }



    //  分页查询
    @Override
    public Object page(CategoryRequest categoryRequest) {
        PageHelper.startPage(categoryRequest.getPageNum(), categoryRequest.getPageSize());
        //自关联查询
        List<Category> categories = categoryMapper.listByCondition(categoryRequest);
        return new PageInfo<>(categories);
    }
    //新增
    @Override
    public void save(Category obj) {
        categoryMapper.save(obj);
    }
    //通过id查询
    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public void update(Category obj) {
        categoryMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.delete(id);
    }

}
