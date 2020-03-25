package com.hb.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("tb_log")
public class Log {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField
    private String requestUrl;

    @TableField
    private String createBy;

    @TableField
    private LocalDateTime createDate;
}
