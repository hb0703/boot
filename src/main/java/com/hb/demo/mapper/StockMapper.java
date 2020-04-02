package com.hb.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hb.demo.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {


    public int updateStockById(Long id);

    /**
     * 悲观锁
     * @param id
     * @return
     */
    public Stock findStockById(Long id);

    /**
     * 乐观锁
     * @param stock
     * @return
     */
    public int updateStockByIdAndVersion(Stock stock);

    /**
     * 更新的时候库存大于0判断
     * @param stock
     * @return
     */
    public int updateStockByIdAndStorage(Stock stock);


}
