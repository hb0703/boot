package com.hb.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb.demo.entity.Log;
import com.hb.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends BaseMapper<Log> {


}
