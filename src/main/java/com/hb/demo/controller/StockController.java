package com.hb.demo.controller;


import com.hb.demo.api.R;
import com.hb.demo.entity.Stock;
import com.hb.demo.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StockController {

    StockService stockService;

    @GetMapping("miaosha")
    public R miaosha(@RequestParam("id")Long id) {
        return stockService.miaosha(id);
    }


}
