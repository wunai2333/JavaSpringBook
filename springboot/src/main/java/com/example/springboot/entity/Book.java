package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity implements Serializable {

    private String name;
    private String description;
    private String publish;
    private String author;
    private String publisher;
    private String category;
    private String bookNo;
    private String cover;

    private List<String> categories;
    private Integer score;
    private Integer nums;

}
