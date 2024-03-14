package com.example.springboot.service.Imp;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BookRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImp implements BookService {


    @Autowired
    private BookMapper bookMapper;

    //    查询全部数据
    @Override
    public List<Book> list() {
        return bookMapper.list();
    }
    //  分页查询
    @Override
    public Object page(BookRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Book> list = bookMapper.listByCondition(baseRequest);
        return new PageInfo<>(list);
    }

    //新增
    @Override
    public void save(Book obj) {
        try {
            obj.setCategory(category(obj.getCategories()));
            bookMapper.save(obj);
        } catch (Exception e) {
         throw new ServiceException("数据插入错误", e);
        }
    }
    //通过id查询
    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book obj) {
        try {
            obj.setCategory(category(obj.getCategories()));
            bookMapper.updateById(obj);
        } catch (Exception e) {
            throw new ServiceException("数据更新错误", e);
        }

    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.delete(id);
    }

    private String category(List<String> categories) {
        StringBuilder sb = new StringBuilder();
        if (CollUtil.isNotEmpty(categories)) {
            categories.forEach(v -> sb.append(v).append(" > "));
            return sb.substring(0, sb.lastIndexOf(" > "));
        }
        return sb.toString();
    }

}
