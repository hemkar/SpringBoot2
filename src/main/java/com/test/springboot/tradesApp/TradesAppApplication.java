package com.ms.trade.springboot.tradesApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ms.trade.springboot.beans.Trades;
import com.ms.trade.springboot.service.TradesService;

@SpringBootApplication
@ComponentScan("com.ms.trade.springboot")
public class TradesAppApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(TradesAppApplication.class);
	@Autowired
	TradesService service;
	Boolean validtradeDate = false;
	Boolean validSettelementDate = false;
	public static final String dateFormat = "MM/dd/yyyy";

	public static void main(String[] args) {
		SpringApplication.run(TradesAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		BufferedReader reader = null;
		FileReader fReader = null;
		ValidatorFactory factory = null;
		String settlementDate = null;
		String tradeDate = null;
		Validator validator = null;

		try {
			fReader = new FileReader("TradeData.dat");
			reader = new BufferedReader(fReader);
			String line = reader.readLine();
			line = reader.readLine();
			factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
			while (line != null) {
				try{
				String[] object = line.split("\\|");
				if (object.length < 8 || object.length > 8) {
					logger.error("Insufficient data in file : - {}", line);
				} else {
					tradeDate = object[5].trim();
					Date tradeD = new SimpleDateFormat(dateFormat).parse(tradeDate);
					settlementDate = object[6].trim();
					Date settlementD = new SimpleDateFormat(dateFormat).parse(settlementDate);										
					if (isValidFormat(dateFormat, tradeDate, Locale.ENGLISH)
							&& isValidFormat(dateFormat, settlementDate, Locale.ENGLISH)
							&& settlementD.after(tradeD)) {

						Trades trade = new Trades(object[0].trim(), object[1].trim(), object[2].trim(),
								object[3].trim(), new Integer(object[4].trim()), tradeD, settlementD, object[7].trim());
						Set<ConstraintViolation<Trades>> violations = validator.validate(trade);

						if (violations.isEmpty()) {
							service.insert(trade);
						} else {
							for(ConstraintViolation<Trades> violation: violations ){
								logger.error("Invalid data in file : - {} {} {}",line,violation.getPropertyPath(), violation.getMessage());
							}
						}

					} else {
						logger.error("Invalid date : - {}", line);
					}
				}

				line = reader.readLine();
				}catch(Exception e){
					logger.error("Invalid data in file : - {} {}", e.getMessage(),line);
					line = reader.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("Input file  not available",e);
		}
		finally {
			if (reader != null)
				reader.close();

			if (fReader != null)
				fReader.close();

			if (factory != null)
				factory.close();

		}

	}

	public static boolean isValidFormat(String format, String value, Locale locale) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					logger.error("Exception while parsing date ",e2);
				}
			}
		}

		return false;
	}
}
