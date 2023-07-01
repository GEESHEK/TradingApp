package com.gee.repository;

import com.gee.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Integer> {

    boolean existsStockById(Integer id);
    List<Stock> findStockByInvestmentAccountIdAndTicker(Integer id, String ticker);
//    List<Stock> findStockByInvestmentAccount(InvestmentAccount investmentAccount);
}
