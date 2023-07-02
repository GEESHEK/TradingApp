package com.gee.service;

import com.gee.doa.StockDao;
import com.gee.exception.RequestValidationException;
import com.gee.exception.ResourceNotFoundException;
import com.gee.model.InvestmentAccount;
import com.gee.model.Stock;
import com.gee.record.StockBuyRequest;
import com.gee.record.StockSellRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    private final StockDao stockDao;
    private final InvestmentAccountService investmentAccountService;

    public StockService(@Qualifier("stock_jpa") StockDao stockDao, InvestmentAccountService investmentAccountService) {
        this.stockDao = stockDao;
        this.investmentAccountService = investmentAccountService;
    }

    public List<Stock> getAllStocks() {
        return stockDao.selectAllStock();
    }

    public Stock getStock(Integer id) {
        return stockDao.selectStockById(id)
                .orElseThrow(() -> new ResourceNotFoundException("stock with id [%s] not found".formatted(id)));
    }

    //method that can return only a specific ticker symbol based on the account.
    // this will return an empty array if either does not exist. Could throw exception if you want.
    public List<Stock> findStockByTickerOrderByDateTimeOfPurchaseDescending(Integer id, String ticker) {
        return stockDao.findStockByTickerOrderByDateTimeOfPurchaseDescending(id, ticker);
    }

    //method that returns all stock for an account this can actually be done when I retrieve the data from investment account.

    public void buyStock(Integer investmentAccountId, StockBuyRequest stockBuyRequest) {
        //check if account exists and retrieve it.
        InvestmentAccount investmentAccount = investmentAccountService.getInvestmentAccount(investmentAccountId);
        //check stock ticker > if exist on yahoo.ticker check then okay!
        String ticker = stockBuyRequest.ticker();
        //check if account has enough money
        Integer stockPrice = 100;
        //save a time, for now just have one time but in future can add in queues for when markets are closed.
        LocalDateTime timeOfPurchase = LocalDateTime.now();
        double units = stockBuyRequest.unit();
        Integer cost = (int) (stockPrice * units) * 100; //come back to check if the math checks out
        if (investmentAccount.getBalance() < cost) {
            throw new RequestValidationException("Non-Sufficient Funds, transaction cannot be completed");
        }
        //for loop to create the objects
        while (units > 0) {
            if (units > 1) {
                stockDao.insertStock(new Stock(ticker, stockPrice, null, 1.00, "USD", false, timeOfPurchase, null, investmentAccount));
            } else {
                stockDao.insertStock(new Stock(ticker, stockPrice, null, units, "USD", false, timeOfPurchase, null, investmentAccount));
            }
            units -= 1;
        }

    }

    public void sellStock(Integer investmentAccountId, StockSellRequest stockSellRequest) {

        //check stock ticker > if exist on yahoo.ticker check then okay!
        String ticker = stockSellRequest.ticker();

        //filter all stocks
        List<Stock> stockListFilteredFIFO = stockDao.findStockByInvestmentAccountIdAndTickerOrderByDateTimeOfPurchaseAscending(investmentAccountId, stockSellRequest.ticker());
        double unitsHeld = 0.00;
        //Calculate units held by account //maybe SQL in future :D
        for (Stock s : stockListFilteredFIFO) {
            unitsHeld += s.getQuantity();
        }

        if (stockSellRequest.unit() > unitsHeld) {
            throw new RequestValidationException("cannot not sell stocks due to non-sufficient amount of share held");
        }

//        for (double unitsSold = 0; i < unitsHeld; unitsSold  ) {
//           stockListFilteredFIFO
//        }

        double unitsSold = 0.00;
        int index = 0;

        while (unitsSold < stockSellRequest.unit()) {
            Stock stockToBeSold = stockListFilteredFIFO.get(index);
            double quantityOfUnitHeld = stockListFilteredFIFO.get(index).getQuantity();
            if(stockSellRequest.unit() > 1 && quantityOfUnitHeld == 1) {
                unitsSold += quantityOfUnitHeld;
                stockToBeSold.setQuantity(0.00);
                stockToBeSold.setSold(true);
                stockDao.updateStock(stockToBeSold);
            }


        }
    }


}
