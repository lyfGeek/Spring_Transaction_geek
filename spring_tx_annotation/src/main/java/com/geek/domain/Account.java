package com.geek.domain;

import lombok.Data;

/**
 * 账户实体类。
 */
@Data
public class Account {
    private Integer id;
    private String name;
    private Float money;
}
