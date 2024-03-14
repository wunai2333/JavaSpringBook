package com.example.springboot.service;

import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.CategoryRequest;
import com.example.springboot.entity.Category;

import java.util.List;

public interface CategoryService {


    List<Category> list();

    Object page(CategoryRequest categoryRequest);

    void save(Category obj);

    Category getById(Integer id);

    void update(Category obj);

    void deleteById(Integer id);
    
}
