package com.gee.doa;

import com.gee.model.Stock;
import com.gee.repository.StockRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("stock_jpa")
public class StockJPADataAccessService implements StockDao {

    private final StockRepository stockRepository;

    public StockJPADataAccessService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> selectAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> selectStockById(Integer id) {
        return stockRepository.findById(id);
    }

    @Override
    public void insertStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public void deleteStockById(Integer stockId) {
        stockRepository.deleteById(stockId);
    }

    @Override
    public boolean existsStockWithId(Integer id) {
        return stockRepository.existsStockById(id);
    }

    @Override
    public void updateStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Override
    public List<Stock> findStockByTickerOrderByDateTimeOfPurchaseDescending(Integer id, String ticker) {
        return stockRepository.findStockByInvestmentAccountIdAndTickerOrderByDateTimeOfPurchaseDesc(id, ticker);
    }

    @Override
    public List<Stock> findStockByInvestmentAccountIdAndTickerOrderByDateTimeOfPurchaseAscending(Integer id, String ticker) {
        return stockRepository.findStockByInvestmentAccountIdAndTickerOrderByDateTimeOfPurchase(id, ticker);
    }
}
