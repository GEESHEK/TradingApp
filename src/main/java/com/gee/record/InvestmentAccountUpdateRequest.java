package com.gee.record;

public record InvestmentAccountUpdateRequest(String username, String email, String password, Integer alterBalance) {
}
