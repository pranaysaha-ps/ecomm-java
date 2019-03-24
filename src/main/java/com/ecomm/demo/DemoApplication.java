package com.ecomm.demo;

import com.ecomm.demo.model.Inventory;
import com.ecomm.demo.model.UserAccount;
import com.ecomm.demo.repository.InventoryRepository;
import com.ecomm.demo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		* Uncomment the following code to create db with pre-filled data and then comment it to use the ID of respective
		* user or product for testing using test cases. Replace the values in test cases.
		* */

//		inventoryRepository.deleteAll();
//
//		// save a couple of products
//		inventoryRepository.save(new Inventory("Nike-Shoe", 10, 1000.0));
//		inventoryRepository.save(new Inventory("Panasonc - Earphones", 20, 500.0));
//
//
//		userAccountRepository.deleteAll();
//
//		// save a couple of users
//		userAccountRepository.save(new UserAccount("pranaysaha", "ecomm123", "Pranay Saha"));
//		userAccountRepository.save(new UserAccount("anonymous", "ecomm123", "Anonymous"));
	}

}
