package com.marketingapp2.entity;

import javax.persistence.*;

@Entity
@Table(name = "leads")
//when the entity class name and database table name is not same,
//when we want the names are to be different, use this annotation- '@Table'
public class Lead {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	//In database when there are multiple words like'firstName', we can not use camel casing
	//use snake casing like 'first_name, thats why here use this annotation-> @Column
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false , unique = true)
	private String email;
	
	@Column(name="mobile", nullable = false , unique = true)
	private long mobile;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

}
