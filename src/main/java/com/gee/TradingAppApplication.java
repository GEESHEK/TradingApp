package com.gee;

import com.gee.model.InvestmentAccount;
import com.gee.model.Stock;
import com.gee.repository.InvestmentAccountRepository;
import com.gee.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TradingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(InvestmentAccountRepository investmentAccountRepository, StockRepository stockRepository) {
        return args -> {
            InvestmentAccount gee = new InvestmentAccount("Gee Chong Shek","geeshek1995","geeshek@gmail.com", LocalDate.of(1995,10,03),"12345",200000);
            InvestmentAccount yuko = new InvestmentAccount("Yuko Chan","yuko2000","yuko@gmail.com",LocalDate.of(1994,8,11),"123456",150000);

            List<InvestmentAccount> investmentAccounts = List.of(gee,yuko);
            investmentAccountRepository.saveAll(investmentAccounts);

            Stock tesla = new Stock("TSLA",20000,null,1.00,"USD",false, LocalDateTime.of(2023,06,21,16,51),null,gee);
            Stock tesla1 = new Stock("TSLA",25000,null,1.50,"USD",false, LocalDateTime.of(2023,06,21,16,51),null,gee);
            Stock google = new Stock("GOOG",20000,null,1.00,"USD",false, LocalDateTime.of(2023,06,21,16,51),null,gee);
            Stock tesla2 = new Stock("TSLA",20000,null,1.00,"USD",false, LocalDateTime.of(2023,06,21,16,51),null,yuko);
            List<Stock> stocks = List.of(tesla,tesla1,google,tesla2);
            stockRepository.saveAll(stocks);

//            List<Stock> stockOwned = stockRepository.findAllStockByIdAndTicker(1,"TSLA");
//            System.out.println("-------------------------------------------------------------------------------------------");
//            stockOwned.forEach(System.out::println);

            //flyaway dependency will break this
            //Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Sequence "INVESTMENT_ACCOUNT_SEQ" not found; SQL statement:
            //select next value for investment_account_seq [90036-214]
        };
    }

}
