package com.gee.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class InvestmentAccount {

    @Id
    @SequenceGenerator(
            name = "investmentAccount_id_sequence",
            sequenceName = "investmentAccount_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "investmentAccount_id_sequence"
    )
    private Integer id;
    @Column(nullable = false, unique = true) //come back might need to add uniqueConstraints
    private String username;
    @Column(nullable = false, unique = true) //come back might need to add uniqueConstraints
    private String email;
    @Column(nullable = false)
    private String password;
    private int balance; //look into this, online says not to use double

    public InvestmentAccount() {
    }

    public InvestmentAccount(String username, String email, String password, int balance) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public InvestmentAccount(Integer id, String username, String email, String password, int balance) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvestmentAccount that = (InvestmentAccount) o;
        return balance == that.balance && Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, balance);
    }

    @Override
    public String toString() {
        return "InvestmentAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
