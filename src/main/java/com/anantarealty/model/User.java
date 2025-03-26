package com.anantarealty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Email(message = "Please enter a valid email")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Username is required")
	@Column(unique = true)
	private String username;

	@NotBlank(message = "Password is required")
	private String password; // Added password attribute

	@Enumerated(EnumType.STRING)
	@NotBlank(message = "Profile is required")
	private Profile profile;

	private String role;

	private String mobile;

	private String reportingTo;

	private String userStatus;

	private Boolean loginAllowed;

	@Column(unique = true)
	@NotBlank(message = "User ID is required")
	private String userid;

	// Getters and Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Boolean getLoginAllowed() {
		return loginAllowed;
	}

	public void setLoginAllowed(Boolean loginAllowed) {
		this.loginAllowed = loginAllowed;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
