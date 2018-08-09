package com.test.springboot.dao;

import java.util.List;

import com.test.springboot.beans.Trades;

public interface TradeDaoInterface {

	List<Trades> tradesForBroker(String brokerCode);

	List<Trades> getTopTrade(String buySellIndicator);

	int insert(Trades trade);

	Integer getCount(String tradeId);

}