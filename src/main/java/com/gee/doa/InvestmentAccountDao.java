package com.gee.doa;

import com.gee.model.InvestmentAccount;

import java.util.List;
import java.util.Optional;

public interface InvestmentAccountDao {
    List<InvestmentAccount> selectAllInvestmentAccount();
    Optional<InvestmentAccount> selectInvestmentAccountById(Integer id);
    void insertInvestmentAccount(InvestmentAccount investmentAccount);
    boolean existPersonWithEmail(String email);
    boolean existPersonWithUsername(String email);
    void deleteInvestmentAccountById(Integer investmentAccountId);
    boolean existsPersonWithId(Integer id);
    void updateInvestmentAccount(InvestmentAccount investmentAccount);
}
