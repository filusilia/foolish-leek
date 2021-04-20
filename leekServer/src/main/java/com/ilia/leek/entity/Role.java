package com.ilia.leek.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alice on 2021/4/19
 * @version 1.0
 * @since 1.0
 * 角色表
 */
@Data
@TableName(value = "leek.ft_role")
public class Role implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色编号
     */
    @TableField(value = "role_code")
    private String roleCode;

    /**
     * 排序
     */
    @TableField(value = "role_sort")
    private Integer roleSort;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private String status;

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

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ROLE_NAME = "role_name";

    public static final String COL_ROLE_CODE = "role_code";

    public static final String COL_ROLE_SORT = "role_sort";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_REMARK = "remark";
}