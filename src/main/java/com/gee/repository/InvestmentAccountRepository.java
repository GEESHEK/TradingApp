package com.gee.repository;

import com.gee.model.InvestmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository > not needed, done by default for us
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount, Integer> {

    boolean existsInvestmentAccountByEmail(String email);
    boolean existsInvestmentAccountByUsername(String email);
    boolean existsInvestmentAccountById(Integer id);

}
