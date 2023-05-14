package com.gee;

import com.gee.model.InvestmentAccount;
import com.gee.repository.InvestmentAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TradingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(InvestmentAccountRepository investmentAccountRepository) {
        return args -> {
            InvestmentAccount gee = new InvestmentAccount("geeshek1995","geeshek@gmail.com","12345",200000);
            InvestmentAccount yuko = new InvestmentAccount("yuko2000","yuko@gmail.com","123456",150000);

            List<InvestmentAccount> investmentAccounts = List.of(gee,yuko);
            investmentAccountRepository.saveAll(investmentAccounts);
            //flyaway dependency will break this
            //Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Sequence "INVESTMENT_ACCOUNT_SEQ" not found; SQL statement:
            //select next value for investment_account_seq [90036-214]
        };
    }

}
