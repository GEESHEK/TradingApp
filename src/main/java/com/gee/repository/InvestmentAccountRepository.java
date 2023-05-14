package com.gee.repository;

import com.gee.model.InvestmentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository > not needed, done by default for us
public interface InvestmentAccountRepository extends JpaRepository<InvestmentAccount,Long> {



}
