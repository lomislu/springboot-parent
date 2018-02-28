package com.coeho.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端返回结果
 *
 * @author: Lomis Lu (http://blog.coeho.com)
 * @email: lomis.lu@gmail.com
 * @datetime: 2018-02-12 18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String code = "200";

    private String msg = "success";

    private Object data;

}
