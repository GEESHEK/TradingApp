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
        int stockPrice = 100; //in pence
        //save a time, for now just have one time but in future can add in queues for when markets are closed.
        LocalDateTime timeOfPurchase = LocalDateTime.now();
        double units = stockBuyRequest.unit();
        int cost = (int) (stockPrice * units) * 100; //come back to check if the math checks out
        int accountBalance = investmentAccount.getBalance();
        if (accountBalance < cost) {
            throw new RequestValidationException("Non-Sufficient Funds, transaction cannot be completed");
        }


        //for loop to create the objects //also decrease the balance
        while (units > 0) {
            if (units > 1) {
                stockDao.insertStock(new Stock(ticker, stockPrice, null, 1.00, "USD", false, timeOfPurchase, null, investmentAccount));
            } else {
                stockDao.insertStock(new Stock(ticker, stockPrice, null, units, "USD", false, timeOfPurchase, null, investmentAccount));
            }
            units -= 1;
        }
        //updating the balance after purchase
        investmentAccount.setBalance(accountBalance - cost); //100 pence off, cost 22800 and balance 177300 = 200100 when I buy tesla > cost 22700
        investmentAccountService.updateInvestmentAccountBalance(investmentAccount);

    }

    public void sellStock(Integer investmentAccountId, StockSellRequest stockSellRequest) {
        //get investment account to increase balance every time you sell a stock // come back to do for now just have total sale value
        InvestmentAccount investmentAccount = investmentAccountService.getInvestmentAccount(investmentAccountId);

        //check stock ticker > if exist on yahoo.ticker check then okay!
        String ticker = stockSellRequest.ticker();

        //filter all stocks
        List<Stock> stockListFilteredFIFO = stockDao.findStockByInvestmentAccountIdAndTickerOrderByDateTimeOfPurchaseAscending(investmentAccountId, stockSellRequest.ticker());
        double unitsHeld = 0.00;
        //Calculate units held by account //maybe SQL in future :D
        for (Stock s : stockListFilteredFIFO) {
            unitsHeld += s.getQuantity();
        }

        double unitsToBeSold = stockSellRequest.unit();
        if (unitsToBeSold > unitsHeld) {
            throw new RequestValidationException("cannot not sell stocks due to non-sufficient amount of share held");
        }

//        for (double unitsSold = 0; i < unitsHeld; unitsSold  ) {
//           stockListFilteredFIFO
//        }

        double unitsSold = 0.00;
        int index = 0;

        while (unitsSold < unitsToBeSold) {
            Stock stockToBeSold = stockListFilteredFIFO.get(index);
            double quantityOfUnitHeld = stockToBeSold.getQuantity();
            //handles 1sell to 1held or 1sell and 0.5held
            if(unitsToBeSold >= 1.00 && quantityOfUnitHeld <= 1.00) {
                unitsSold += quantityOfUnitHeld;
                stockToBeSold.setQuantity(0.00);
                stockToBeSold.setIsSold(true);
                stockDao.updateStock(stockToBeSold);
                index++;
            } else { //handles sell 0.4 and held 0.6 = held 0.2 (quantityOfUnitHeld > unitsToBeSold)
                double remainder = unitsToBeSold - unitsSold;
                unitsSold += remainder;
                stockToBeSold.setQuantity(quantityOfUnitHeld - remainder);
                stockToBeSold.setIsSold(true);
                stockDao.updateStock(stockToBeSold);
                index++;
            }
        }

        //increase balance and call yahoo api
        int stockPrice = 200;
        int sellTotal = (int)(unitsToBeSold * stockPrice)*100;
        int accountBalance = investmentAccount.getBalance();
        investmentAccount.setBalance(accountBalance + sellTotal);
        investmentAccountService.updateInvestmentAccountBalance(investmentAccount);
    }


}
