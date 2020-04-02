package com.hb.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@TableName("tb_stock")
public class Stock implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField
    private Long storage;

    @TableField
    private Long goodsId;

    @TableField
    private LocalDateTime createDate;

    @TableField
    private String createBy;

    @TableField
    private Integer version;


}
