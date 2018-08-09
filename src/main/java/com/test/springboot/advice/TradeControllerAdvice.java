package com.test.springboot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.test.springboot.beans.TradeNotFound;
import com.test.springboot.exception.TradeNotFoundException;



@ControllerAdvice
public class TradeControllerAdvice {

	
	@ExceptionHandler
	public ResponseEntity<TradeNotFound> handleStudentNotFoudException(TradeNotFoundException ex,WebRequest request){
		TradeNotFound stnf= new TradeNotFound();
		stnf.setBody(ex.getMessage());
		stnf.setStatus(HttpStatus.NOT_FOUND.value());
		stnf.setTime(System.currentTimeMillis());
		
		return new ResponseEntity(stnf, HttpStatus.NOT_FOUND);
		
	}
	
}
