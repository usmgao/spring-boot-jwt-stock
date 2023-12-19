package com.bezkoder.springjwt.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.StockName;
import com.bezkoder.springjwt.repository.StockNameRepository;

//import com.ming.boot.model.StockName;
//import com.ming.boot.repository.StockNameRepository;

/* Created by Ming  */
@RestController
//@CrossOrigin("http://localhost:3000")
public class StockNameSearchController {

	@Autowired
	private StockNameRepository stockNameRepository;

	@GetMapping("/stock_search")
	public ResponseEntity<List<StockName>> getStockNameFunction(@RequestParam String symbol, @RequestParam String functionName) {
		System.out.println("========= from /stock_search path ======== " + LocalDateTime.now());
		System.out.println("getStockFunctionResponseValue: " + symbol + ", " + functionName);
		List<StockName> partialMatchList = new ArrayList();

		if (functionName.equals("match_all")) {
			partialMatchList = stockNameRepository.findByPartialMatch(symbol);
			System.out.println("partialMatchList size: " + partialMatchList.size());			
		} else if(functionName.equals("match_name_only")) {
			partialMatchList = stockNameRepository.findByStartingWith(symbol);
			System.out.println("partialMatchList size: " + partialMatchList.size());
		} else {
			System.out.println("functionName " + functionName + " is not existed");
		}

		return  ResponseEntity.ok(partialMatchList);
	}
}