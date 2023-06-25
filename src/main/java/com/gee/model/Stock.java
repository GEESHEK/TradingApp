package com.gee.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Stock {

    @Id
    @SequenceGenerator(
            name = "stock_id_sequence",
            sequenceName = "stock_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_id_sequence"
    )
    private Integer id;
    @Column(nullable = false)
    private String ticker;
    @Column(nullable = false)
    private Integer purchasePrice;
    //    @Column(nullable = false)
    private Integer salePrice;
    @Column(nullable = false)
    private Double quantity;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private Boolean isSold;
    @Column(nullable = false)
    private LocalDateTime dateTimeOfPurchase;
    //    @Column(nullable = false)
    private LocalDateTime dateTimeOfSale;
    @ManyToOne
    @JoinColumn(name = "investmentAccountId")
//    @JsonIgnoreProperties("stock")
    private InvestmentAccount investmentAccount;

    public Stock() {
    }

    public Stock(String ticker, Integer purchasePrice, Integer salePrice, Double quantity, String currency, Boolean isSold, LocalDateTime dateTimeOfPurchase, LocalDateTime dateTimeOfSale, InvestmentAccount investmentAccount) {
        this.ticker = ticker;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.currency = currency;
        this.isSold = isSold;
        this.dateTimeOfPurchase = dateTimeOfPurchase;
        this.dateTimeOfSale = dateTimeOfSale;
        this.investmentAccount = investmentAccount;
    }

    public Stock(Integer id, String ticker, Integer purchasePrice, Integer salePrice, Double quantity, String currency, Boolean isSold, LocalDateTime dateTimeOfPurchase, LocalDateTime dateTimeOfSale, InvestmentAccount investmentAccount) {
        this.id = id;
        this.ticker = ticker;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.currency = currency;
        this.isSold = isSold;
        this.dateTimeOfPurchase = dateTimeOfPurchase;
        this.dateTimeOfSale = dateTimeOfSale;
        this.investmentAccount = investmentAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Integer salePrice) {
        this.salePrice = salePrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public LocalDateTime getDateTimeOfPurchase() {
        return dateTimeOfPurchase;
    }

    public void setDateTimeOfPurchase(LocalDateTime dateTimeOfPurchase) {
        this.dateTimeOfPurchase = dateTimeOfPurchase;
    }

    public LocalDateTime getDateTimeOfSale() {
        return dateTimeOfSale;
    }

    public void setDateTimeOfSale(LocalDateTime dateTimeOfSale) {
        this.dateTimeOfSale = dateTimeOfSale;
    }

    public InvestmentAccount getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(InvestmentAccount investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) && Objects.equals(ticker, stock.ticker) && Objects.equals(purchasePrice, stock.purchasePrice) && Objects.equals(salePrice, stock.salePrice) && Objects.equals(quantity, stock.quantity) && Objects.equals(currency, stock.currency) && Objects.equals(isSold, stock.isSold) && Objects.equals(dateTimeOfPurchase, stock.dateTimeOfPurchase) && Objects.equals(dateTimeOfSale, stock.dateTimeOfSale) && Objects.equals(investmentAccount, stock.investmentAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticker, purchasePrice, salePrice, quantity, currency, isSold, dateTimeOfPurchase, dateTimeOfSale, investmentAccount);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", quantity=" + quantity +
                ", currency='" + currency + '\'' +
                ", isSold=" + isSold +
                ", dateTimeOfPurchase=" + dateTimeOfPurchase +
                ", dateTimeOfSale=" + dateTimeOfSale +
                ", investmentAccount=" + investmentAccount +
                '}';
    }
}
