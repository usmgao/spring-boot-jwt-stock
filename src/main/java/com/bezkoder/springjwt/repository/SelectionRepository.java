package com.bezkoder.springjwt.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.Selection;
import com.bezkoder.springjwt.models.User;

public interface SelectionRepository extends JpaRepository<Selection, Long> {
	List<Selection> findByUser(User user);
}