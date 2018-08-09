package com.test.springboot.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Trades {

	@Id
	@NotNull
	@Size(min = 1)
	@Pattern(regexp = "^[a-zA-Z0-9]")
	private String tradeId;
	
	@NotNull
	@Size(min = 1)
	@Pattern(regexp = "^[a-zA-Z0-9]")
	private String stockName;
	
	@NotNull
	@Size(min = 1)
	@Pattern(regexp = "^[a-zA-Z0-9]")
	private String brokerCode;
	
	@NotNull
	@Size(min = 1)
	@Pattern(regexp = "^[a-zA-Z0-9]")
	private String brokerName;
	
	@NotNull
	private int quantity;
	
	private Date tradeDate;
	private Date settlementDate;
	
	@NotNull
	private String  buySellIndicator;

	@AssertTrue
	  private boolean isValid() {
		if(this.settlementDate.compareTo(this.tradeDate)>1){
			return true;
		}
	    return false;
	  }
	

	public String getTradeId() {
		return tradeId;
	}


	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}


	public String getStockName() {
		return stockName;
	}


	public void setStockName(String stockName) {
		this.stockName = stockName;
	}


	public String getBrokerCode() {
		return brokerCode;
	}


	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}


	public String getBrokerName() {
		return brokerName;
	}


	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Date getTradeDate() {
		return tradeDate;
	}


	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}


	public Date getSettlementDate() {
		return settlementDate;
	}


	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}


	


	public String getBuySellIndicator() {
		return buySellIndicator;
	}


	public void setBuySellIndicator(String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}

	public Trades(){
		
	}

	public Trades(@NotNull @Size(min = 1) @Pattern(regexp = "^[a-zA-Z0-9]") String tradeId,
			@NotNull @Size(min = 1) @Pattern(regexp = "^[a-zA-Z0-9]") String stockName,
			@NotNull @Size(min = 1) @Pattern(regexp = "^[a-zA-Z0-9]") String brokerCode,
			@NotNull @Size(min = 1) @Pattern(regexp = "^[a-zA-Z0-9]") String brokerName, @NotNull int quantity,
			Date tradeDate, Date settlementDate, @NotNull String buySellIndicator) {
		super();
		this.tradeId = tradeId;
		this.stockName = stockName;
		this.brokerCode = brokerCode;
		this.brokerName = brokerName;
		this.quantity = quantity;
		this.tradeDate = tradeDate;
		this.settlementDate = settlementDate;
		this.buySellIndicator = buySellIndicator;
	}


	@Override
	public String toString() {
		return "Trades [tradeId=" + tradeId + ", stockName=" + stockName + ", brokerCode=" + brokerCode
				+ ", brokerName=" + brokerName + ", quantity=" + quantity + ", tradeDate=" + tradeDate
				+ ", settlementDate=" + settlementDate + ", buySellIndicator=" + buySellIndicator + "]";
	}

}
