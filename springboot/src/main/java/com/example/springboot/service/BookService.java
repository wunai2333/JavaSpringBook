package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.BookRequest;
import com.example.springboot.controller.request.CategoryRequest;
import com.example.springboot.entity.Book;

import java.util.List;

public interface BookService {


    List<Book> list();

    //  分页查询
    Object page(BookRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void update(Book obj);

    void deleteById(Integer id);
    
}
