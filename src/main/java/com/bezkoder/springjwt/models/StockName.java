package com.bezkoder.springjwt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

@Entity
@Table(name = "stockslist")
public class StockName {

	@Id
	@Column(name = "symbol", length = 10, nullable = false)
	private String symbol;

	@Column(name = "company_name", length = 145)
	private String companyName;

	@Column(name = "industry", length = 45)
	private String industry;

	@Column(name = "market_cap")
	private Long marketCap;

	// Constructors, getters, and setters

	public StockName() {
	}

	public StockName(String symbol, String companyName, String industry, Long marketCap) {
		this.symbol = symbol;
		this.companyName = companyName;
		this.industry = industry;
		this.marketCap = marketCap;
	}

	// Getters and setters

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Long getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Long marketCap) {
		this.marketCap = marketCap;
	}

	// toString() method (optional)

	@Override
	public String toString() {
		return "StockEntity{" + "symbol='" + symbol + '\'' + ", companyName='" + companyName + '\'' + ", industry='"
				+ industry + '\'' + ", marketCap=" + marketCap + '}';
	}
}
