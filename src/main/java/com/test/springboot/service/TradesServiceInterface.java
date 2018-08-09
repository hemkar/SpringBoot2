package com.test.springboot.service;

import java.util.List;

import com.test.springboot.beans.Trades;

public interface TradesServiceInterface {

	List<Trades> getTradesForBroker(String brokerId);

	List<Trades> getTopTrade(String buySellIndicator);
	
	public void insert(Trades trade);

	Integer getCount(String tradeId);

}