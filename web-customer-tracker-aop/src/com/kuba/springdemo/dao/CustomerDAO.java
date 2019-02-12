package com.kuba.springdemo.dao;

import java.util.List;

import com.kuba.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theId);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	
}
