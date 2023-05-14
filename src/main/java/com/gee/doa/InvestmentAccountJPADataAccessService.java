package com.gee.doa;

import com.gee.model.InvestmentAccount;
import com.gee.repository.InvestmentAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
public class InvestmentAccountJPADataAccessService implements InvestmentAccountDAO {

    private final InvestmentAccountRepository investmentAccountRepository;

    public InvestmentAccountJPADataAccessService(InvestmentAccountRepository investmentAccountRepository) {
        this.investmentAccountRepository = investmentAccountRepository;
    }

    @Override
    public List<InvestmentAccount> selectAllInvestmentAccount() {
        return investmentAccountRepository.findAll();
    }
}
