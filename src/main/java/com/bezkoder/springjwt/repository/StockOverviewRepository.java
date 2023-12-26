package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.StockOverview;

//import com.ming.boot.model.StockOverview;
@Repository
public interface StockOverviewRepository extends JpaRepository<StockOverview,Long> {
	public Optional<StockOverview> findBySymbol(String symbol);
	//public Optional<StockOverview> findById(String id);
	public StockOverview findBySymbolAndUserId(String symbol, String userId);
	public List<StockOverview> findByUserId(String userId);
}
