package com.gee.controller;

import com.gee.model.InvestmentAccount;
import com.gee.service.InvestmentAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/investmentAccounts")
public class InvestmentAccountController {

    private final InvestmentAccountService investmentAccountService;

    public InvestmentAccountController(InvestmentAccountService investmentAccountService) {
        this.investmentAccountService = investmentAccountService;
    }

    @GetMapping
    public List<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccountService.getInvestmentAccounts();
    }


}
