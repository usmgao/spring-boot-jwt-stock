package com.bezkoder.springjwt.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	private String name;

	@JsonManagedReference
	@ManyToMany(mappedBy = "users")
	private List<StockOverview> stockOverviews = new ArrayList<>();

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StockOverview> getStockOverviews() {
		if (stockOverviews == null) {
			stockOverviews = new ArrayList<>();
		}
		return stockOverviews;
	}

	public void setStockOverviews(List<StockOverview> stockOverviews) {
		this.stockOverviews = stockOverviews;
	}

	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		return toSimpleString();
	}

	public String toSimpleString() {
		String s = "";
		for (int i = 0; i < stockOverviews.size(); i++) {
			StockOverview st = stockOverviews.get(i);
			s = s + ", [" + st.getSymbol() + ", " + st.getId() + "]";
		}
		return "id: " + id + ", username: " + username + ", email: " + email + ", stockOverviews: " + s;
	}
}
