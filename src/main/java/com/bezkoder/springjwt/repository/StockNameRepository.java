package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bezkoder.springjwt.models.StockName;

//import com.ming.boot.model.StockName;

public interface StockNameRepository extends JpaRepository<StockName, String> {
	@Query("SELECT s FROM StockName s WHERE LOWER(s.symbol) LIKE LOWER(CONCAT('%', :query, '%')) "
			+ "OR LOWER(s.companyName) LIKE LOWER(CONCAT('%', :query, '%')) "
			+ "OR LOWER(s.industry) LIKE LOWER(CONCAT('%', :query, '%'))")
	List<StockName> findByPartialMatch(@Param("query") String query);

	@Query("SELECT s FROM StockName s WHERE LOWER(s.symbol) LIKE LOWER(CONCAT(:query, '%')) "
			+ "OR LOWER(s.companyName) LIKE LOWER(CONCAT(:query, '%')) "
			+ "OR LOWER(s.industry) LIKE LOWER(CONCAT(:query, '%'))")
	List<StockName> findByStartingWith(@Param("query") String query);

	List<StockName> findBySymbolContainingIgnoreCase(String symbol);
	List<StockName> findBySymbolStartingWithIgnoreCase(String symbol);
	//List<StockName> findBySymbolLike(String symbol);
	
	@Query("SELECT s FROM StockName s WHERE LOWER(s.symbol) LIKE LOWER(CONCAT(:searchTerm, '%'))")
	List<StockName> findBySymbolLike(@Param("searchTerm") String searchTerm);
}
