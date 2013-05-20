package org.shop.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Customer implements Serializable{


	private static final long serialVersionUID = 1L;
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Timestamp registrationDate;
	private String customerCellPhone;
	private int addressId;
	
	

	public Customer(String firstName, String lastName, String email, String password) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getCustomerCellPhone() {
		return customerCellPhone;
	}

	public void setCustomerCellPhone(String customerCellPhone) {
		this.customerCellPhone = customerCellPhone;
	}
	public int getAddressId() {
		return addressId;
	}
	
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", registrationDate="
				+ registrationDate + ", customerCellPhone=" + customerCellPhone
				+ ", addressId=" + addressId + "]";
	}
}