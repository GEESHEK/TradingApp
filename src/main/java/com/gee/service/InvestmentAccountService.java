package com.gee.service;

import com.gee.doa.InvestmentAccountDao;
import com.gee.model.InvestmentAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentAccountService {

    private final InvestmentAccountDao investmentAccountDao;
    //@Qualifier > change DAO implementation e.g. JDBCTemplate
    public InvestmentAccountService(@Qualifier("jpa") InvestmentAccountDao investmentAccountDao) {
        this.investmentAccountDao = investmentAccountDao;
    }

    public List<InvestmentAccount> getAllInvestmentAccounts() {
        return investmentAccountDao.selectAllInvestmentAccount();
    }

    public InvestmentAccount getInvestmentAccount(Integer id) {
        return investmentAccountDao.selectInvestmentAccountById(id)
                .orElseThrow(() -> new IllegalArgumentException("id [%s] not found".formatted(id)));
    }
}
