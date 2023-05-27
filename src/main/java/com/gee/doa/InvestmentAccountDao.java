package com.gee.doa;

import com.gee.model.InvestmentAccount;

import java.util.List;
import java.util.Optional;

public interface InvestmentAccountDao {
    List<InvestmentAccount> selectAllInvestmentAccount();
    Optional<InvestmentAccount> selectInvestmentAccountById(Integer id);
}
