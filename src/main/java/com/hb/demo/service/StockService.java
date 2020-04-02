package com.hb.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hb.demo.api.R;
import com.hb.demo.entity.Stock;
import com.hb.demo.mapper.StockMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;


@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class StockService extends ServiceImpl<StockMapper, Stock> {

    private static Logger logger = LoggerFactory.getLogger(StockService.class);

    /**
     * 悲观锁 查询时加上 for update
     */
    @Transactional(readOnly = false)
    public R miaoshaBeiguan(Long id){
        Stock stock = baseMapper.findStockById(id);
       try {
           Long storage = stock.getStorage();
           if(storage <= 0) {
               return R.fail("fail");
           }
           int i = baseMapper.updateStockById(id);
           if(i == 0) {
               logger.info("线程：{}：秒杀失败",Thread.currentThread().getName());
           }else{
               logger.info("线程：{}：秒杀成功",Thread.currentThread().getName());
           }
       }catch (Exception e) {
           throw new RuntimeException();
       }
        return R.success("success！");
    }


    /**
     * 乐观锁 version 字段  ，库存大于0才更新
     */
    @Transactional(readOnly = false)
    public R miaoshaLeguan(Long id){
        Stock stock = baseMapper.selectById(id);
        try {
            Long storage = stock.getStorage();
            if(storage <= 0) {
                return R.fail("fail");
            }
            int i = baseMapper.updateStockById(id);
            if(i == 0) {
                logger.info("线程：{}：秒杀失败",Thread.currentThread().getName());
            }else{
                logger.info("线程：{}：秒杀成功",Thread.currentThread().getName());
            }
        }catch (Exception e) {
            throw new RuntimeException();
        }
        return R.success("success！");
    }
}
