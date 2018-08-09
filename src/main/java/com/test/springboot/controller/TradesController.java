package com.test.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.springboot.beans.Trades;
import com.test.springboot.exception.TradeNotFoundException;
import com.test.springboot.service.TradesServiceInterface;

@RestController
@RequestMapping("/trades")
public class TradesController {

	@Autowired
	TradesServiceInterface tradeService;
	
	@GetMapping("forbroker/{brokerCode}")
	public List<Trades> getTradeForBroker(@PathVariable String brokerCode){
		
		List<Trades> trades=tradeService.getTradesForBroker(brokerCode);
		if(trades.isEmpty()){
			throw new TradeNotFoundException("No Trade found for brokerCode :"+brokerCode);
		}
		return trades;
		
	}
	
	@GetMapping("topstocks/{buySellIndicator}")
	public List<Trades> getTopTrade(@PathVariable String buySellIndicator) {
		
		List<Trades> trades=tradeService.getTopTrade(buySellIndicator);
		if(trades.isEmpty()){
			throw new TradeNotFoundException("No Trade found for buySellIndicator :"+ buySellIndicator);
		}
		return trades;
	}
	
	@GetMapping("/hi")
	public String test(){
		return "hello";
	}
}
