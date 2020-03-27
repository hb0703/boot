package com.hb.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("tb_user")
public class User {

    @TableId(type = IdType.ID_WORKER_STR)
    private String userId;

    @TableField
    private String userName;
}
