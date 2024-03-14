package com.example.springboot.service.Imp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.BookRequest;
import com.example.springboot.controller.request.BorrowReTURcOUNTPO;
import com.example.springboot.controller.request.BorrowRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.BorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BorrowServiceImp implements BorrowService {


    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BookMapper bookMapper;


    //    查询全部数据
    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }
    //  分页查询
    @Override
    public Object page(BorrowRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        for (Borrow borrow : borrows) {
            LocalDate returnDate = borrow.getReturnDate();
            LocalDate now = LocalDate.now();
            if (now.plusDays(1).isEqual(returnDate)) {
                borrow.setNote("即将到期");
            } else if (now.isEqual(returnDate)) {
                borrow.setNote("已到期");
            } else if (now.isAfter(returnDate)) {
                borrow.setNote("已过期");
            } else {
                borrow.setNote("正常");
            }
        }
        return new PageInfo<>(borrows);
    }

    //新增
    @Override
    @Transactional
    public void save(Borrow obj) {
        // 报错，先不要这个模块
        //1.校验用户积分是否足够
        String userName = obj.getUserName();
        User user = userMapper.getByUsername(userName);
        if (Objects.isNull(user)) {
            throw new ServiceException("用户不存在");
        }
        //2.校验图书数量是否足够
        Book book = bookMapper.getByNo(obj.getBookNo());
        if (Objects.isNull(book)) {
            throw new ServiceException("所借书不存在");
        }
        //4.校验图书数量
        if (book.getNums() < 1) {
            throw new ServiceException("图书数量不足");
        }
        Integer account = user.getAccount();
        Integer score = book.getScore() * obj.getDays(); // 借 1本的积分 * 天数
        //3.校验用户积分余额
        if (score > account) {
            throw new ServiceException("用户积分不足");
        }
        //4.校验图书数量
        if (book.getNums() < 1) {
            throw new ServiceException("图书数量不足");
        }
        //更新数据
        user.setAccount(user.getAccount() - score);
        userMapper.updateById(user);
        //更新书的数量
        book.setNums(book.getNums() - 1);
        bookMapper.updateById(book);

        obj.setReturnDate(LocalDate.now().plus(obj.getDays(), ChronoUnit.DAYS));// 当前日期 加 days 得到归还日期
        // 新增借书记录
        borrowMapper.save(obj);
    }
    //通过id查询
    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow obj) {
        obj.setUpdatetime(LocalDate.now());
        borrowMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.delete(id);
    }

    // 借书模块
    @Override
    public Object pageRetur(BorrowRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        return new PageInfo<>(borrowMapper.listReturByCondition(baseRequest));
    }


    @Transactional
    @Override
    public void saveRetur(Retur obj) {
        // 改状态
        obj.setStatus("已归还");

        borrowMapper.updateStatus("已归还", obj.getId());

        // 图书数量增加

        obj.setRealDate(LocalDate.now());
        borrowMapper.saveRetur(obj);

        bookMapper.updateNumByNo(obj.getBookNo());
    }

    @Override
    public void deleteReturById(Integer id) {
        borrowMapper.deleteReturById(id);
    }

    // 统计
    @Override
    public Map<String, Object> getCountByTimeRange(String timeRange) {
        Map<String, Object> map = new HashMap<>();
        Date today = new Date();
        List<DateTime> dateRange;
        switch (timeRange) {
            case "week":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -6), today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -29), today, DateField.DAY_OF_WEEK);
                break;
            case "month2":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -59), today, DateField.DAY_OF_WEEK);
                break;
            case "month3":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -89), today, DateField.DAY_OF_WEEK);
                break;
            default:
                dateRange = new ArrayList<>();
        }
        List<String> dateStrRange = datetimeToDateStr(dateRange);
        map.put("date", dateStrRange);
        List<BorrowReTURcOUNTPO> borrowCount = borrowMapper.getCountByTimeRange(timeRange, 1);
        List<BorrowReTURcOUNTPO> returnCount = borrowMapper.getCountByTimeRange(timeRange, 2);
        map.put("borrow", countList(borrowCount, dateStrRange));
        map.put("retur", countList(returnCount, dateStrRange));
        return map;
    }

    private List<Integer> countList(List<BorrowReTURcOUNTPO> countPOlist, List<String> dateRange) {
        List<Integer> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(countPOlist)) {
            return list;
        }
        for (String date : dateRange) {
            Integer count = countPOlist.stream().filter(countPO -> date.equals(countPO.getDate()))
                    .map(BorrowReTURcOUNTPO :: getCount).findFirst().orElse(0);
            list.add(count);
        }
        return list;

    }

    private List<String> datetimeToDateStr(List<DateTime> dateTimeList) {
        List<String> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(dateTimeList)) {
            return list;
        }
        for (DateTime dateTime : dateTimeList) {
            String date = DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }

}
