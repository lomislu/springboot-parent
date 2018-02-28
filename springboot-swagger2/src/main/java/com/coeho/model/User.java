package com.coeho.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-12 18:04
 */
@Data
public class User {

    private int id;

    private String nickname;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String address;

    private String status;

    private Date createDate;

    private Date modifyDate;

}
