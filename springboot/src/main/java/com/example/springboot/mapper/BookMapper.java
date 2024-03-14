package com.example.springboot.mapper;

import com.example.springboot.controller.request.BookRequest;
import com.example.springboot.controller.request.CategoryRequest;
import com.example.springboot.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> list();

    List<Book> listByCondition(BookRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void updateById(Book obj);

    void delete(Integer id);

    Book getByNo(Integer bookNo);

    void updateNumByNo(Integer bookNo);


    //Book getByUsernameAndPassWord(LoginRequest loginRequest);
}
