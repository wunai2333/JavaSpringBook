package com.example.springboot.controller.request;


import lombok.Data;

@Data
public class CategoryRequest extends BaseRequest{
    private String name;
    private String remark;
    private int pid;
}
