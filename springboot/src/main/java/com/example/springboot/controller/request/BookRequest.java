package com.example.springboot.controller.request;


import lombok.Data;

@Data
public class BookRequest extends BaseRequest{
    private String name;
    private String remark;
    private int pid;
}
