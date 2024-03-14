package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Retur {

    /**
     * id
     */
    private Integer id;
    /**
     * 图书名称
     */
    private String bookName;
    /**
     * 图书编号
     */
    private Integer bookNo;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createtime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate updatetime;

    /**
     * 借书积分
     */
    private Integer score;
    private String status;
    private Integer days;
    private LocalDate returnDate;
    private LocalDate realDate;

}
