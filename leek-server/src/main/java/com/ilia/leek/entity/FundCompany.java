package com.ilia.leek.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 * 基金公司表
 */
@Data
@TableName(value = "leek.ft_fund_company")
public class FundCompany implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司代码
     */
    @TableField(value = "company_code")
    private String companyCode;

    /**
     * 公司名称
     */
    @TableField(value = "company_name")
    private String companyName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundCompany company = (FundCompany) o;
        return Objects.equals(companyCode, company.companyCode) && Objects.equals(companyName, company.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode, companyName);
    }

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_COMPANY_CODE = "company_code";

    public static final String COL_COMPANY_NAME = "company_name";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}