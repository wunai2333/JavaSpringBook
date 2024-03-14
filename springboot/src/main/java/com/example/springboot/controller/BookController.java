package com.example.springboot.controller;

import com.example.springboot.conmmon.Result;
import com.example.springboot.controller.request.BookRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.service.Imp.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceImp bookServiceImp;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Book obj){
        bookServiceImp.save(obj);
        return Result.success();
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody Book obj){
        bookServiceImp.update(obj);
        return Result.success();
    }
    //通过id删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        bookServiceImp.deleteById(id);
        return Result.success();
    }

    //通过id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book obj = bookServiceImp.getById(id);
        bookServiceImp.getById(id);
        return Result.success(obj);
    }

    //查询全部数据
    @GetMapping("/list")
    public Result list() {
        List<Book> list = bookServiceImp.list();
        return Result.success(list);
    }
    //分页查询
    @GetMapping("/page")
    public Result page(BookRequest bookRequest) {

        return Result.success(bookServiceImp.page(bookRequest));
    }
}
