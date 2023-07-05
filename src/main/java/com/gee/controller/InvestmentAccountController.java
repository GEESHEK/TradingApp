package com.gee.controller;

import com.gee.model.InvestmentAccount;
import com.gee.record.InvestmentAccountRegistrationRequest;
import com.gee.record.InvestmentAccountUpdateRequest;
import com.gee.service.InvestmentAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/investmentAccounts")
public class InvestmentAccountController {

    private final InvestmentAccountService investmentAccountService;

    public InvestmentAccountController(InvestmentAccountService investmentAccountService) {
        this.investmentAccountService = investmentAccountService;
    }

    @GetMapping //remove this late, should not be able to see everyone's accounts
    public List<InvestmentAccount> getInvestmentAccounts() {
        return investmentAccountService.getAllInvestmentAccounts();
    }

    @GetMapping("{investmentAccountId}")
    public InvestmentAccount getInvestmentAccount(@PathVariable("investmentAccountId") Integer investmentAccountId) {
        return investmentAccountService.getInvestmentAccount(investmentAccountId);
    }

    @PostMapping
    public void registerInvestmentAccount(@RequestBody InvestmentAccountRegistrationRequest request) {
        investmentAccountService.addInvestmentAccount(request);
    }

    @DeleteMapping("{investmentAccountId}")
    public void deleteInvestmentAccount(@PathVariable("investmentAccountId") Integer investmentAccountId) {
        investmentAccountService.deleteInvestmentAccountById(investmentAccountId);
    }

    @PutMapping("{investmentAccountId}")
    public void updateInvestmentAccount(@PathVariable("investmentAccountId") Integer investmentAccountId, @RequestBody InvestmentAccountUpdateRequest request) {
        investmentAccountService.updateInvestmentAccount(investmentAccountId, request);
    }

}
