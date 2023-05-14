package com.gee.doa;

import com.gee.model.InvestmentAccount;

import java.util.List;

public interface InvestmentAccountDAO {
    List<InvestmentAccount> selectAllInvestmentAccount();
}
