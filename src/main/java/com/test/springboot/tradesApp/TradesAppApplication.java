package com.test.springboot.tradesApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.test.springboot.beans.Trades;
import com.test.springboot.service.TradesService;

@SpringBootApplication
@ComponentScan("com.test.springboot")
public class TradesAppApplication implements CommandLineRunner {

	
	@Autowired
	TradesService service;
	
	public static void main(String[] args)  {
		SpringApplication.run(TradesAppApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("TradeData.dat"));
			String line = reader.readLine();
			line = reader.readLine();
			while (line != null) {
				String[] object = line.split("\\|");
				if (object.length < 8 || object.length > 8) {
					System.out.println("Invalid data in file :"+line);
				} else {
					Trades trade = new Trades(object[0].trim(), object[1].trim(), object[2].trim(), object[3].trim(),
							new Integer(object[4].trim()), new SimpleDateFormat("MM/dd/yyyy").parse(object[5].trim()),
							new SimpleDateFormat("MM/dd/yyyy").parse(object[6].trim()), object[7].trim());
					service.insert(trade);
				}

				line = reader.readLine();

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
