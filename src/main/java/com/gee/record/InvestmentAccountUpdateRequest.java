package com.gee.record;

public record InvestmentAccountUpdateRequest(String name,String username, String email, String password, Integer alterBalance) {
}
