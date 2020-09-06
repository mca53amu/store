package com.store.retail;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.store.retail.service.InvoiceService;

@Profile("test")
@Configuration
public class ProductServiceTestConfiguration {
	@Bean
	@Primary
	public InvoiceService productService() {
		return Mockito.mock(InvoiceService.class);
	}
}