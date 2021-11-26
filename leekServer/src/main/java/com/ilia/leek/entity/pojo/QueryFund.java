package com.ilia.leek.entity.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 * 查询基金使用pojo
 */
@Data
public class QueryFund implements Serializable {
    private static final long serialVersionUID = 4284160512058976181L;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 是否收藏
     */
    @ApiModelProperty("是否收藏")
    private String favorite;

    /**
     * 排序查询
     */
    @ApiModelProperty("排序")
    private Integer order;

    /**
     * 基金代码
     */
    @ApiModelProperty("基金代码")
    private String fundCode;
    /**
     * 基金名称(模糊查询)
     */
    @ApiModelProperty("基金名称(模糊查询)")
    private String fundName;
    /**
     * 基金的拼音名称(模糊查询)
     */
    @ApiModelProperty("基金的拼音名称(模糊查询)")
    private String pinyin;
    /**
     * 基金类型(模糊查询)
     */
    @ApiModelProperty("基金类型(模糊查询)")
    private String fundType;
    /**
     * 公司代码
     */
    @ApiModelProperty("公司代码")
    private String companyCode;
    /**
     * 公司名称(模糊查询)
     */
    @ApiModelProperty("公司名称(模糊查询)")
    private String companyName;

    /**
     * 页码(分页)
     */
    @ApiModelProperty("页码(分页)")
    private int page;

    /**
     * 每页数量
     */
    @ApiModelProperty("每页数量")
    private int pageSize;
}
