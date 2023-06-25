package com.gee.service;

import com.gee.doa.StockDao;
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

    public void buyStock(StockBuyRequest stockBuyRequest) {
        //check stock price
        //check stock ticker
        //check if account exists
        //check if account has enough money
        //for loop to create the objects
        //save a time, for now just have one time but in future can add in queues for when markets are closed.

        //only when all checks are done
        LocalDateTime localDateTime = LocalDateTime.now();

        Stock stock = new Stock(

        );
        stockDao.insertStock(stock);
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
