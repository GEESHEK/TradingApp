package com.gee.doa;

import com.gee.model.InvestmentAccount;
import com.gee.repository.InvestmentAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class InvestmentAccountJPADataAccessService implements InvestmentAccountDao {

    private final InvestmentAccountRepository investmentAccountRepository;

    public InvestmentAccountJPADataAccessService(InvestmentAccountRepository investmentAccountRepository) {
        this.investmentAccountRepository = investmentAccountRepository;
    }

    @Override
    public List<InvestmentAccount> selectAllInvestmentAccount() {
        return investmentAccountRepository.findAll();
    }

    @Override
    public Optional<InvestmentAccount> selectInvestmentAccountById(Integer id) {
        return investmentAccountRepository.findById(id);
    }

    @Override
    public void insertInvestmentAccount(InvestmentAccount investmentAccount) {
        investmentAccountRepository.save(investmentAccount);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return investmentAccountRepository.existsInvestmentAccountByEmail(email);
    }

    @Override
    public boolean existPersonWithUsername(String email) {
        return investmentAccountRepository.existsInvestmentAccountByUsername(email);
    }


}
