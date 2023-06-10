package com.gee.service;

import com.gee.doa.InvestmentAccountDao;
import com.gee.exception.DuplicateResourceException;
import com.gee.exception.RequestValidationException;
import com.gee.exception.ResourceNotFoundException;
import com.gee.model.InvestmentAccount;
import com.gee.record.InvestmentAccountRegistrationRequest;
import com.gee.record.InvestmentAccountUpdateRequest;
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

        existPersonWithUsername(username);
        existPersonWithEmail(email);
        
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

    public void updateInvestmentAccount(Integer investmentAccountId, InvestmentAccountUpdateRequest updateRequest) {
        InvestmentAccount investmentAccount = getInvestmentAccount(investmentAccountId);

        boolean changes = false;

        String username = updateRequest.username();
        String email = updateRequest.email();
        String password = updateRequest.password();
        Integer alterBalance = updateRequest.alterBalance();

        if(username != null && !username.equals(investmentAccount.getUsername())) {
            existPersonWithUsername(username);
            investmentAccount.setUsername(username);
            changes = true;
        }

        if(email != null && !email.equals(investmentAccount.getEmail())) {
            existPersonWithEmail(email);
            investmentAccount.setEmail(email);
            changes = true;
        }

        if(password != null && !password.equals(investmentAccount.getPassword())) {
            investmentAccount.setPassword(password);
            changes = true;
        }

        if(alterBalance != null) {
            investmentAccount.setBalance(investmentAccount.getBalance() + alterBalance);
            changes = true;
        }

        if(!changes) {
           throw new RequestValidationException("no data changes found");
        }

        investmentAccountDao.updateInvestmentAccount(investmentAccount);
    }

    private void existPersonWithUsername(String username) {
        if(investmentAccountDao.existPersonWithUsername(username)) {
            throw new DuplicateResourceException("username already taken");
        }
    }

    private void existPersonWithEmail(String email) {
        if(investmentAccountDao.existPersonWithEmail(email)) {
            throw new DuplicateResourceException("email already taken");
        }
    }
}
