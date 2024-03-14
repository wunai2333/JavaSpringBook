package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.BookRequest;
import com.example.springboot.controller.request.BorrowRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BorrowService {


    List<Borrow> list();

    //  分页查询
    Object page(BorrowRequest baseRequest);

    void save(Borrow obj);

    Borrow getById(Integer id);

    void update(Borrow obj);

    void deleteById(Integer id);

    // 借书模块  分页查询
    Object pageRetur(BorrowRequest baseRequest);

    void saveRetur(Retur obj);

    void deleteReturById(Integer id);

    // 统计
    Object getCountByTimeRange(String timeRange);
}
