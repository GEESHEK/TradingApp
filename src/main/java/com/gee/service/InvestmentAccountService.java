package com.gee.service;

import com.gee.doa.InvestmentAccountDao;
import com.gee.exception.DuplicateResourceException;
import com.gee.exception.ResourceNotFoundException;
import com.gee.model.InvestmentAccount;
import com.gee.record.InvestmentAccountRegistrationRequest;
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
                .orElseThrow(() -> new ResourceNotFoundException("investment account with id [%s] not found".formatted(id)));
    }

    public void addInvestmentAccount(InvestmentAccountRegistrationRequest investmentAccountRegistrationRequest) {
        String username = investmentAccountRegistrationRequest.username();
        String email = investmentAccountRegistrationRequest.email();

        if(investmentAccountDao.existPersonWithUsername(username)) {
            throw new DuplicateResourceException("username already taken");
        }

        if(investmentAccountDao.existPersonWithEmail(email)) {
            throw new DuplicateResourceException("email already taken");
        }


        InvestmentAccount investmentAccount = new InvestmentAccount(
                investmentAccountRegistrationRequest.username(),
                investmentAccountRegistrationRequest.email(),
                investmentAccountRegistrationRequest.password(),
                0
        );
        investmentAccountDao.insertInvestmentAccount(investmentAccount);
    }

    public void deleteInvestmentAccountById(Integer investmentAccountId) {
        if(!investmentAccountDao.existsPersonWithId(investmentAccountId)) {
            throw new ResourceNotFoundException(
                    "investment account with id [%s] not found".formatted(investmentAccountId)
            );
        }

        investmentAccountDao.deleteInvestmentAccountById(investmentAccountId);
    }
}
