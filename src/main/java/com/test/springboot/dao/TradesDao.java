package com.test.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.test.springboot.beans.Trades;

@Repository
public class TradesDao implements TradeDaoInterface {

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	@Override
	public List<Trades> tradesForBroker(String brokerCode) {
		return jdbcTemplate.query("select * from trade where brokerCode = ?", new Object[] { brokerCode },
				new BeanPropertyRowMapper(Trades.class));

	}

	@Override
	public List<Trades> getTopTrade(String buySellIndicator) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from trade where buySellIndicator = ? order by quantity desc",
				new Object[] { buySellIndicator }, new BeanPropertyRowMapper(Trades.class));
	}
	
	@Override
	public  Integer getCount(String tradeId){
		
		return jdbcTemplate.queryForObject(" select count(*) from trade where tradeId =?", new Object[]{tradeId}, Integer.class);
		  
	}

	@Override
	public int insert(Trades trade) {
		return jdbcTemplate.update(
				" insert into trade (tradeId,stockName,brokerCode,brokerName,quantity,tradeDate,settlementDate,buySellIndicator) "
						+ " values(?,?,?,?,?,?,?,?)",
				new Object[] { trade.getTradeId(), trade.getStockName(), trade.getBrokerCode(), trade.getBrokerName(),
						trade.getQuantity(), trade.getTradeDate(), trade.getSettlementDate(),
						trade.getBuySellIndicator() });

	}
}
