package com.store.retail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.store.retail.service.InvoiceRepository;

@SpringBootTest
class StoreApplicationTests {
	@Autowired
	InvoiceRepository inovoicerepo;

	@Test
	void contextLoads() {

	}

}
