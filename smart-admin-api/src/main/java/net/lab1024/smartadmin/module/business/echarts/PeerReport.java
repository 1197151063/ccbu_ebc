package net.lab1024.smartadmin.module.business.echarts;

public class PeerReport {
    private Integer totalAssets;
    private Integer retainedProfits;
    private Double shortLoanShare;
    private Double middleLoanShare;
    private Double shortDepositShare;
    private Double longDepositShare;
    private Double stockPrice;

    public Integer getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Integer totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Integer getRetainedProfits() {
        return retainedProfits;
    }

    public void setRetainedProfits(Integer retainedProfits) {
        this.retainedProfits = retainedProfits;
    }

    public Double getShortLoanShare() {
        return shortLoanShare;
    }

    public void setShortLoanShare(Double shortLoanShare) {
        this.shortLoanShare = shortLoanShare;
    }

    public Double getMiddleLoanShare() {
        return middleLoanShare;
    }

    public void setMiddleLoanShare(Double middleLoanShare) {
        this.middleLoanShare = middleLoanShare;
    }

    public Double getShortDepositShare() {
        return shortDepositShare;
    }

    public void setShortDepositShare(Double shortDepositShare) {
        this.shortDepositShare = shortDepositShare;
    }

    public Double getLongDepositShare() {
        return longDepositShare;
    }

    public void setLongDepositShare(Double longDepositShare) {
        this.longDepositShare = longDepositShare;
    }

    public Double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }
}
