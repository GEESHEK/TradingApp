package com.gee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class InvestmentAccount {

    @Id
    @SequenceGenerator(
            name = "investment_account_id_sequence",
            sequenceName = "investment_account_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "investmentAccount_id_sequence"
    )
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true) //come back might need to add uniqueConstraints
    private String username;
    @Column(nullable = false, unique = true) //come back might need to add uniqueConstraints
    private String email;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @Column(nullable = false)
    private String password;
    private Integer balance; //look into this, online says not to use double
    @OneToMany(
            mappedBy = "investmentAccount",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("investmentAccount")
    private List<Stock> stocks = new ArrayList<>();

    public InvestmentAccount() {
    }

    public InvestmentAccount(String name, String username, String email, LocalDate dateOfBirth, String password, Integer balance) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.balance = balance;
    }

    public InvestmentAccount(Integer id, String name, String username, String email, LocalDate dateOfBirth, String password, Integer balance) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks.addAll(stocks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestmentAccount that = (InvestmentAccount) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(password, that.password) && Objects.equals(balance, that.balance) && Objects.equals(stocks, that.stocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, dateOfBirth, password, balance, stocks);
    }

    @Override
    public String toString() {
        return "InvestmentAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", stocks=" + stocks +
                '}';
    }
}
