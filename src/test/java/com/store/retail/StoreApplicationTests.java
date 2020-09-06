package com.store.retail;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.store.retail.response.InvoiceReponse;
import com.store.retail.service.InvoiceService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class StoreApplicationTests {
	@Autowired
	InvoiceService invoiceService;

	@Test
	public void testSTAFF() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer("STAFF", 950)
				.orElseThrow(Exception::new);
		assertEquals(665.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testAFFILIATE() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer("AFFILIATE", 950)
				.orElseThrow(Exception::new);
		assertEquals(855.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testEXISTINGUSER() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer("EXISTINGUSER", 950)
				.orElseThrow(Exception::new);
		assertEquals(902.5, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testNORMALUSER() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer("NORMALUSER", 950)
				.orElseThrow(Exception::new);
		assertEquals(905.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

}
