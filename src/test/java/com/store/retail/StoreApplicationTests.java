package com.store.retail;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.store.retail.response.InvoiceReponse;
import com.store.retail.service.InvoiceService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class StoreApplicationTests {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	private MockMvc mockMvc;

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

	@Test
	public void testPREMIUMR() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer("PREMIUM", 950)
				.orElseThrow(Exception::new);
		assertEquals(760.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testController() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store/invoice/10")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
	}

}
