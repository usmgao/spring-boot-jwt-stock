package com.bezkoder.springjwt.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class ColumnSelection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 120)
	private String columnsSelectionName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "columnselection_stockoverviewcolumn", joinColumns = @JoinColumn(name = "columnselection_id"), inverseJoinColumns = @JoinColumn(name = "stockoverviewcolumn_id"))
	private List<StockOverviewColumn> columns = new ArrayList<>();


	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "ColumnSelection_User", joinColumns = @JoinColumn(name = "columnSelection_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColumnsSelectionName() {
		return columnsSelectionName;
	}

	public void setColumnsSelectionName(String columnsSelectionName) {
		this.columnsSelectionName = columnsSelectionName;
	}

	public List<StockOverviewColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<StockOverviewColumn> columns) {
		this.columns = columns;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
