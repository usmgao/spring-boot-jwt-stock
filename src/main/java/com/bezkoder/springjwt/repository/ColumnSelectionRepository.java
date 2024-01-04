package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.ColumnSelection;
import com.bezkoder.springjwt.models.StockOverview;
import com.bezkoder.springjwt.models.User;

@Repository
public interface ColumnSelectionRepository extends JpaRepository<ColumnSelection,Long> {

}
