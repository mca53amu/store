package com.store.retail;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
	private static final String PREMIUM = "PREMIUM";
	private static final String NORMALUSER = "NORMALUSER";
	private static final String EXISTINGUSER = "EXISTINGUSER";
	private static final String AFFILIATE = "AFFILIATE";
	private static final String STAFF = "STAFF";
	private static final int INVOICE_ID=10;
	private static final int CUSTOMER_ID=10;
	private static final double AMOUNT=950;
	
	@Mock
	InvoiceService invoiceService;
	@Autowired
	private MockMvc mockMvc;

	public void setUP() {

	}

	@Test
	public void testSTAFF() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer(STAFF, AMOUNT)
				.orElseThrow(Exception::new);
		assertEquals(665.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testAFFILIATE() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer(AFFILIATE, AMOUNT)
				.orElseThrow(Exception::new);
		assertEquals(855.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testEXISTINGUSER() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer(EXISTINGUSER, AMOUNT)
				.orElseThrow(Exception::new);
		assertEquals(902.5, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testNORMALUSER() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer(NORMALUSER, AMOUNT)
				.orElseThrow(Exception::new);
		assertEquals(905.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testPREMIUMR() throws Exception {
		InvoiceReponse invoiceRespone = invoiceService.generateInvoiceNewCustomer(PREMIUM, AMOUNT)
				.orElseThrow(Exception::new);
		assertEquals(760.0, invoiceRespone.getNetPayAmount(), 0.5);
	}

	@Test
	public void testController() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store/invoice/"+INVOICE_ID)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
	}

	@Test
	public void testController2() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store/customer/AFFILIATE/"+AMOUNT)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testController3() throws Exception {
		MockMvcRequestBuilders.get("/store/customer/AFFILIATE/"+AMOUNT).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store/invoice/"+INVOICE_ID+"/"+AMOUNT)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}

	@Test
	public void testController4() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/store/invoice/"+INVOICE_ID)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
}
