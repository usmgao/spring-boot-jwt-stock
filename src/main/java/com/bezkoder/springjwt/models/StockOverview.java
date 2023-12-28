package com.bezkoder.springjwt.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class StockOverview {

	@Id
	@GeneratedValue
	private Long id;

	@JsonProperty("Symbol")
	@Column(unique = true)
	private String symbol;

	@JsonProperty("AssetType")
	private String assetType;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("Description")
	@Column(length = 1255)
	private String description;

	@JsonProperty("CIK")
	private String cIK;

	@JsonProperty("Exchange")
	private String exchange;

	@JsonProperty("Currency")
	private String currency;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("Sector")
	private String sector;

	@JsonProperty("Industry")
	private String industry;

	@JsonProperty("Address")
	private String address;

	@JsonProperty("FiscalYearEnd")
	private String fiscalYearEnd;

	@JsonProperty("LatestQuarter")
	private String latestQuarter;

	@JsonProperty("MarketCapitalization")
	private String marketCapitalization;

	@JsonProperty("EBITDA")
	private String eBITDA;

	@JsonProperty("PERatio")
	private String pERatio;

	@JsonProperty("PEGRatio")
	private String pEGRatio;

	@JsonProperty("BookValue")
	private String bookValue;

	@JsonProperty("DividendPerShare")
	private String dividendPerShare;

	@JsonProperty("DividendYield")
	private String dividendYield;

	@JsonProperty("EPS")
	private String ePS;

	@JsonProperty("RevenuePerShareTTM")
	private String revenuePerShareTTM;

	@JsonProperty("ProfitMargin")
	private String profitMargin;

	@JsonProperty("OperatingMarginTTM")
	private String operatingMarginTTM;

	@JsonProperty("ReturnOnAssetsTTM")
	private String returnOnAssetsTTM;

	@JsonProperty("ReturnOnEquityTTM")
	private String returnOnEquityTTM;

	@JsonProperty("RevenueTTM")
	private String revenueTTM;

	@JsonProperty("GrossProfitTTM")
	private String grossProfitTTM;

	@JsonProperty("DilutedEPSTTM")
	private String dilutedEPSTTM;

	@JsonProperty("QuarterlyEarningsGrowthYOY")
	private String quarterlyEarningsGrowthYOY;

	@JsonProperty("QuarterlyRevenueGrowthYOY")
	private String quarterlyRevenueGrowthYOY;

	@JsonProperty("AnalystTargetPrice")
	private String analystTargetPrice;

	@JsonProperty("TrailingPE")
	private String trailingPE;

	@JsonProperty("ForwardPE")
	private String forwardPE;

	@JsonProperty("PriceToSalesRatioTTM")
	private String priceToSalesRatioTTM;

	@JsonProperty("PriceToBookRatio")
	private String priceToBookRatio;

	@JsonProperty("EVToRevenue")
	private String eVToRevenue;

	@JsonProperty("EVToEBITDA")
	private String eVToEBITDA;

	@JsonProperty("Beta")
	private String beta;

	@JsonProperty("52WeekHigh")
	private String _52WeekHigh;

	@JsonProperty("52WeekLow")
	private String _52WeekLow;

	@JsonProperty("50DayMovingAverage")
	private String _50DayMovingAverage;

	@JsonProperty("200DayMovingAverage")
	private String _200DayMovingAverage;

	@JsonProperty("SharesOutstanding")
	private String sharesOutstanding;

	@JsonProperty("DividendDate")
	private String dividendDate;

	@JsonProperty("ExDividendDate")
	private String exDividendDate;

	@JsonProperty("UpdateBy")
	private String updateBy;

	@JsonProperty("UpdateDate")
	private Date updateDate;

	@JsonProperty("UserId")
	private String userId;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "StockOverview_User", joinColumns = @JoinColumn(name = "stockOverview_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users = new ArrayList<>();

	private Date updateAt;

	private Date createdAt;

	private String createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getcIK() {
		return cIK;
	}

	public void setcIK(String cIK) {
		this.cIK = cIK;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFiscalYearEnd() {
		return fiscalYearEnd;
	}

	public void setFiscalYearEnd(String fiscalYearEnd) {
		this.fiscalYearEnd = fiscalYearEnd;
	}

	public String getLatestQuarter() {
		return latestQuarter;
	}

	public void setLatestQuarter(String latestQuarter) {
		this.latestQuarter = latestQuarter;
	}

	public String getMarketCapitalization() {
		return marketCapitalization;
	}

	public void setMarketCapitalization(String marketCapitalization) {
		this.marketCapitalization = marketCapitalization;
	}

	public String geteBITDA() {
		return eBITDA;
	}

	public void seteBITDA(String eBITDA) {
		this.eBITDA = eBITDA;
	}

	public String getpERatio() {
		return pERatio;
	}

	public void setpERatio(String pERatio) {
		this.pERatio = pERatio;
	}

	public String getpEGRatio() {
		return pEGRatio;
	}

	public void setpEGRatio(String pEGRatio) {
		this.pEGRatio = pEGRatio;
	}

	public String getBookValue() {
		return bookValue;
	}

	public void setBookValue(String bookValue) {
		this.bookValue = bookValue;
	}

	public String getDividendPerShare() {
		return dividendPerShare;
	}

	public void setDividendPerShare(String dividendPerShare) {
		this.dividendPerShare = dividendPerShare;
	}

	public String getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(String dividendYield) {
		this.dividendYield = dividendYield;
	}

	public String getePS() {
		return ePS;
	}

	public void setePS(String ePS) {
		this.ePS = ePS;
	}

	public String getRevenuePerShareTTM() {
		return revenuePerShareTTM;
	}

	public void setRevenuePerShareTTM(String revenuePerShareTTM) {
		this.revenuePerShareTTM = revenuePerShareTTM;
	}

	public String getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(String profitMargin) {
		this.profitMargin = profitMargin;
	}

	public String getOperatingMarginTTM() {
		return operatingMarginTTM;
	}

	public void setOperatingMarginTTM(String operatingMarginTTM) {
		this.operatingMarginTTM = operatingMarginTTM;
	}

	public String getReturnOnAssetsTTM() {
		return returnOnAssetsTTM;
	}

	public void setReturnOnAssetsTTM(String returnOnAssetsTTM) {
		this.returnOnAssetsTTM = returnOnAssetsTTM;
	}

	public String getReturnOnEquityTTM() {
		return returnOnEquityTTM;
	}

	public void setReturnOnEquityTTM(String returnOnEquityTTM) {
		this.returnOnEquityTTM = returnOnEquityTTM;
	}

	public String getRevenueTTM() {
		return revenueTTM;
	}

	public void setRevenueTTM(String revenueTTM) {
		this.revenueTTM = revenueTTM;
	}

	public String getGrossProfitTTM() {
		return grossProfitTTM;
	}

	public void setGrossProfitTTM(String grossProfitTTM) {
		this.grossProfitTTM = grossProfitTTM;
	}

	public String getDilutedEPSTTM() {
		return dilutedEPSTTM;
	}

	public void setDilutedEPSTTM(String dilutedEPSTTM) {
		this.dilutedEPSTTM = dilutedEPSTTM;
	}

	public String getQuarterlyEarningsGrowthYOY() {
		return quarterlyEarningsGrowthYOY;
	}

	public void setQuarterlyEarningsGrowthYOY(String quarterlyEarningsGrowthYOY) {
		this.quarterlyEarningsGrowthYOY = quarterlyEarningsGrowthYOY;
	}

	public String getQuarterlyRevenueGrowthYOY() {
		return quarterlyRevenueGrowthYOY;
	}

	public void setQuarterlyRevenueGrowthYOY(String quarterlyRevenueGrowthYOY) {
		this.quarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
	}

	public String getAnalystTargetPrice() {
		return analystTargetPrice;
	}

	public void setAnalystTargetPrice(String analystTargetPrice) {
		this.analystTargetPrice = analystTargetPrice;
	}

	public String getTrailingPE() {
		return trailingPE;
	}

	public void setTrailingPE(String trailingPE) {
		this.trailingPE = trailingPE;
	}

	public String getForwardPE() {
		return forwardPE;
	}

	public void setForwardPE(String forwardPE) {
		this.forwardPE = forwardPE;
	}

	public String getPriceToSalesRatioTTM() {
		return priceToSalesRatioTTM;
	}

	public void setPriceToSalesRatioTTM(String priceToSalesRatioTTM) {
		this.priceToSalesRatioTTM = priceToSalesRatioTTM;
	}

	public String getPriceToBookRatio() {
		return priceToBookRatio;
	}

	public void setPriceToBookRatio(String priceToBookRatio) {
		this.priceToBookRatio = priceToBookRatio;
	}

	public String geteVToRevenue() {
		return eVToRevenue;
	}

	public void seteVToRevenue(String eVToRevenue) {
		this.eVToRevenue = eVToRevenue;
	}

	public String geteVToEBITDA() {
		return eVToEBITDA;
	}

	public void seteVToEBITDA(String eVToEBITDA) {
		this.eVToEBITDA = eVToEBITDA;
	}

	public String getBeta() {
		return beta;
	}

	public void setBeta(String beta) {
		this.beta = beta;
	}

	public String get_52WeekHigh() {
		return _52WeekHigh;
	}

	public void set_52WeekHigh(String _52WeekHigh) {
		this._52WeekHigh = _52WeekHigh;
	}

	public String get_52WeekLow() {
		return _52WeekLow;
	}

	public void set_52WeekLow(String _52WeekLow) {
		this._52WeekLow = _52WeekLow;
	}

	public String get_50DayMovingAverage() {
		return _50DayMovingAverage;
	}

	public void set_50DayMovingAverage(String _50DayMovingAverage) {
		this._50DayMovingAverage = _50DayMovingAverage;
	}

	public String get_200DayMovingAverage() {
		return _200DayMovingAverage;
	}

	public void set_200DayMovingAverage(String _200DayMovingAverage) {
		this._200DayMovingAverage = _200DayMovingAverage;
	}

	public String getSharesOutstanding() {
		return sharesOutstanding;
	}

	public void setSharesOutstanding(String sharesOutstanding) {
		this.sharesOutstanding = sharesOutstanding;
	}

	public String getDividendDate() {
		return dividendDate;
	}

	public void setDividendDate(String dividendDate) {
		this.dividendDate = dividendDate;
	}

	public String getExDividendDate() {
		return exDividendDate;
	}

	public void setExDividendDate(String exDividendDate) {
		this.exDividendDate = exDividendDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<User> getUsers() {
		if (users == null) {
			users = new ArrayList<>();
		}
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		return toSimpleString();
	}

	public String toSimpleString() {		
		String userStr = "";
		for(User r : users) {
			userStr = userStr + ", " + r.getName(); 
		}
		return id + ", " + symbol + userStr;
	}
}
