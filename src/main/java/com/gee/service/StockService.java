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
    public List<Stock> getStockByInvestmentAccountIdAndTicker(Integer id, String ticker) {
        return stockDao.findStockByInvestmentAccountIdAndTicker(id, ticker);
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
        Double units = stockBuyRequest.unit();
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
        InvestmentAccount investmentAccount = investmentAccountService.getInvestmentAccount(investmentAccountId);
        List<Stock> stockList = investmentAccount.getStocks();

        //filter all stocks

//        stockList.stream().filter()

//        Stock stock = getStock(stockId);
//
//
//        boolean changes = false;
//
//        String name = updateRequest.name();
//        String username = updateRequest.username();
//        String email = updateRequest.email();
//        String password = updateRequest.password();
//        Integer alterBalance = updateRequest.alterBalance();
//
//        if (name != null && !name.equals(investmentAccount.getName())) {
//            investmentAccount.setName(name);
//            changes = true;
//        }
//
//        if (username != null && !username.equals(investmentAccount.getUsername())) {
//            existPersonWithUsername(username);
//            investmentAccount.setUsername(username);
//            changes = true;
//        }
//
//        if (email != null && !email.equals(investmentAccount.getEmail())) {
//            existPersonWithEmail(email);
//            investmentAccount.setEmail(email);
//            changes = true;
//        }
//
//        if (password != null && !password.equals(investmentAccount.getPassword())) {
//            investmentAccount.setPassword(password);
//            changes = true;
//        }
//
//        if (alterBalance != null) {
//            investmentAccount.setBalance(investmentAccount.getBalance() + alterBalance);
//            changes = true;
//        }
//
//        if (!changes) {
//            throw new RequestValidationException("no data changes found");
//        }
//
//        investmentAccountDao.updateInvestmentAccount(investmentAccount);
    }

//    private void existPersonWithUsername(String username) {
//        if (investmentAccountDao.existPersonWithUsername(username)) {
//            throw new DuplicateResourceException("username already taken");
//        }
//    }
//
//    private void existPersonWithEmail(String email) {
//        if (investmentAccountDao.existPersonWithEmail(email)) {
//            throw new DuplicateResourceException("email already taken");
//        }
//    }
//
//    private void ageChecker(LocalDate dateOfBirth) {
//        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
//
//        if (age < 18) {
//            throw new RequestValidationException("you must be 18 years old to open an investment account");
//        }
//    }

}
