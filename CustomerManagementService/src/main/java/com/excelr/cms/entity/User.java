package com.excelr.cms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userid;
	String username;
	String password;
	LocalDate accountExpiryDate;
	int accountLockedStatus;  //1 - Open   0 - Locked
	LocalDate credentialExpiryDate;
	int accountEnabledStatus; //1 - Enabled   0 - Disabled

	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns = @JoinColumn(name="userid"),
			inverseJoinColumns = @JoinColumn(name="roleid")
			)
	List<Role> roles;
}
