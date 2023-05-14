package com.gee.service;

import com.gee.doa.InvestmentAccountDAO;
import com.gee.model.InvestmentAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentAccountService {

    private final InvestmentAccountDAO investmentAccountDAO;
    //@Qualifier > change DAO implementation e.g. JDBCTemplate
    public InvestmentAccountService(@Qualifier("jpa") InvestmentAccountDAO investmentAccountDAO) {
        this.investmentAccountDAO = investmentAccountDAO;
    }

    public List<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccountDAO.selectAllInvestmentAccount();
    }
}
