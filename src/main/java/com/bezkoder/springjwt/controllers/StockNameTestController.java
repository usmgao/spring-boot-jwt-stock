package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.StockName;
import com.bezkoder.springjwt.repository.StockNameRepository;

//import com.ming.boot.model.StockName;
//import com.ming.boot.repository.StockNameRepository;

@RestController
@RequestMapping("/api/stocks")
//@CrossOrigin(origins = "http://localhost:3000") // Replace with the actual origin of your React app
public class StockNameTestController {
	@Autowired
	private StockNameRepository stockNameRepository;

	@GetMapping("/all")
	public ResponseEntity<List<StockName>> getAllStocks() {
		List<StockName> stocks = stockNameRepository.findAll();
		System.out.println("stocks: " + stocks.size());
		for (int i = 0; i < stocks.size(); i++) {
			System.out.println("stock name: " + stocks.get(i));
		}

		return ResponseEntity.ok(stocks);
	}

	@GetMapping("/search")
	public ResponseEntity<List<StockName>> searchStocks(@RequestParam String query) {
		System.out.println("searchStocks input: " + query);
		List<StockName> matchingStocks = stockNameRepository.findByPartialMatch(query);
		return ResponseEntity.ok(matchingStocks);
	}

	@GetMapping("/symbol_search")
	public ResponseEntity<List<StockName>> searchBySymbol(@RequestParam String symbol) {
		System.out.println("searchBySymbol input: " + symbol);
		List<StockName> matchingStocks = stockNameRepository.findBySymbolLike(symbol + "%");
		return ResponseEntity.ok(matchingStocks);
	}
}
