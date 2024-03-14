package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.BorrowReTURcOUNTPO;
import com.example.springboot.controller.request.BorrowRequest;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BorrowMapper {

    List<Borrow> list();

    List<Borrow> listByCondition(BorrowRequest baseRequest);

    void save(Borrow obj);

    Borrow getById(Integer id);

    void updateById(Borrow obj);

    void delete(Integer id);

    //借书模块

    void saveRetur(Retur obj);

    List<Retur> listReturByCondition(BorrowRequest baseRequest);

    void deleteReturById(Integer id);

    void updateStatus(String status, Integer id);

    // 统计数据
    List<BorrowReTURcOUNTPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);
}
