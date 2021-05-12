package com.ilia.leek.entity.model;

import lombok.Data;

import java.util.List;

/**
 * @author Alice on 2021/5/10
 * 基础返回类
 * @version 1.0
 * @since 1.0
 */
@Data
public class BaseResultModel<T> {
    private Long total;
    private List<T> data;
}
