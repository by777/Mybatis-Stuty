package com.bai.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date create_time;//故意设置不一致,属性名于字段名
    private int views;
}
