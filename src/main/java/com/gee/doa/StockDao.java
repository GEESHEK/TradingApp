package com.gee.doa;

import com.gee.model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockDao {

    List<Stock> selectAllStock();
    Optional<Stock> selectStockById(Integer id);
    void insertStock(Stock stock);
    //    boolean existPersonWithEmail(String email); //need something to check if the stock they are deleting exists
//    boolean existPersonWithUsername(String email);
    void deleteStockById(Integer stockId);
    boolean existsStockWithId(Integer id);
    void updateStock(Stock stock);
    List<Stock> findStockByTickerOrderByDateTimeOfPurchaseDescending(Integer id, String ticker);
    List<Stock> findStockByInvestmentAccountIdAndTickerOrderByDateTimeOfPurchaseAscending(Integer id, String ticker);

}
