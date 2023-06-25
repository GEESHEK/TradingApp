package com.gee.controller;

import com.gee.model.Stock;
import com.gee.record.StockBuyRequest;
import com.gee.record.StockSellRequest;
import com.gee.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/stocks")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("{stockId}")
    public Stock getStockById(@PathVariable("stockId") Integer stockId) {
        return stockService.getStock(stockId);
    }

    @PostMapping
    public void buyStock(@RequestBody StockBuyRequest stockBuyRequest) {
        stockService.buyStock(stockBuyRequest);
    }

    @PutMapping("{investmentAccountId}")
    public void sellStock(@PathVariable("investmentAccountId") Integer investmentAccountId, @RequestBody StockSellRequest stockSellRequest) {
        stockService.sellStock(investmentAccountId, stockSellRequest);
    }

}
