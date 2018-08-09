package com.test.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springboot.beans.Trades;
import com.test.springboot.dao.TradeDaoInterface;

@Service
public class TradesService implements TradesServiceInterface {

	@Autowired
	TradeDaoInterface tradesDao;
	
	@Override
	public List<Trades> getTradesForBroker(String brokerId){
		
		return tradesDao.tradesForBroker(brokerId);
	}

	@Override
	public List<Trades> getTopTrade(String buySellIndicator) {
		return tradesDao.getTopTrade(buySellIndicator);
	}

	@Override
	public void insert(Trades trade) {
		Integer i=getCount(trade.getTradeId());
		if(i>0){
			System.out.println("Duplicate :"+trade);
		}else{
		tradesDao.insert(trade);
		}
	}
	
	@Override
	public Integer getCount(String tradeId){
		return tradesDao.getCount(tradeId);
	}
	
}
