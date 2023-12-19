package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.StockOverview;

//import com.ming.boot.model.StockOverview;

public interface StockOverviewRepository extends JpaRepository<StockOverview,Long> {
	public Optional<StockOverview> findBySymbol(String symbol);
	public StockOverview findBySymbolAndUserId(String symbol, String userId);
	public List<StockOverview> findByUserId(String userId);
}
