package com.ilia.leek.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Alice on 2021/4/26
 * @version 1.0
 * @since 1.0
 * 用户基金表
 */

@Data
@TableName(value = "leek.ft_user_fund")
public class UserFund implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 基金代码
     */
    @TableField(value = "fund_code")
    private String fundCode;

    /**
     * 是否收藏
     * 0- 未收藏
     * 1- 收藏
     */
    @TableField(value = "favorite")
    private String favorite;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

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

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_FUND_CODE = "fund_code";

    public static final String COL_FAVORITE = "favorite";

    public static final String COL_SORT = "sort";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}