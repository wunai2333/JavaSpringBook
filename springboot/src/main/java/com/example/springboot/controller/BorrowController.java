package com.example.springboot.controller;

import com.example.springboot.conmmon.Result;
import com.example.springboot.controller.request.BookRequest;
import com.example.springboot.controller.request.BorrowRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.example.springboot.service.Imp.BorrowServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowServiceImp borrowServiceImp;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Borrow obj){
        borrowServiceImp.save(obj);
        return Result.success();
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody Borrow obj){
        borrowServiceImp.update(obj);
        return Result.success();
    }
    //通过id删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        borrowServiceImp.deleteById(id);
        return Result.success();
    }

    //通过id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Borrow obj = borrowServiceImp.getById(id);
        borrowServiceImp.getById(id);
        return Result.success(obj);
    }

    //查询全部数据
    @GetMapping("/list")
    public Result list() {
        List<Borrow> list = borrowServiceImp.list();
        return Result.success(list);
    }
    //分页查询
    @GetMapping("/page")
    public Result page(BorrowRequest borrowRequest) {

        return Result.success(borrowServiceImp.page(borrowRequest));
    }

    // 借书模块
    //分页查询
    @GetMapping("/pageRetur")
    public Result pageRetur(BorrowRequest borrowRequest) {

        return Result.success(borrowServiceImp.pageRetur(borrowRequest));
    }
    //新增
    @PostMapping("/saveRetur")
    public Result saveRetur(@RequestBody Retur obj){
        borrowServiceImp.saveRetur(obj);
        return Result.success();
    }
    //通过id删除
    @DeleteMapping("/deleteRetur/{id}")
    public Result deleteRetur(@PathVariable Integer id) {
        borrowServiceImp.deleteReturById(id);
        return Result.success();
    }
    // 统计
    @GetMapping("/lineCharts/{timeRange}")
    public Result lineCharts(@PathVariable String timeRange) {
        return Result.success(borrowServiceImp.getCountByTimeRange(timeRange));
    }

}
