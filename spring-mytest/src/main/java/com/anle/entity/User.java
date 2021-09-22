package com.anle.entity;

/**
 * @author AnnLee
 * @version 1.0
 * @date 2021-09-02 10:44
 */
public class User {

	private String email;
	private String userName;

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void showMe(){
        System.out.println("show me test--User");
	}

	public User(String email, String userName) {
		this.email = email;
		this.userName = userName;
	}
}
