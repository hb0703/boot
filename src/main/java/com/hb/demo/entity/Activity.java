package com.hb.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("tb_activity")
public class Activity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField
    private String activityName;

    @TableField
    private Integer activityType;

    /**
     * 0 未开启，1 已开启，2 已结束
     */
    @TableField
    private Integer activityStatus;

    @TableField
    private String activityDesc;

    @TableField
    private LocalDateTime activityStart;

    @TableField
    private LocalDateTime activityEnd;


    @TableField
    private LocalDateTime createDate;

    @TableField
    private String createBy;

}
