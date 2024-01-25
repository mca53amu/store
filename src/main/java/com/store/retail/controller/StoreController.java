/**
 *
 */
package com.store.retail.controller;

import java.util.Optional;
import java.util.concurrent.Semaphore;

import com.store.retail.ratelimit.PricingPlanService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.retail.response.InvoiceReponse;
import com.store.retail.service.InvoiceService;

/**
 * @author Mohammad Miyan
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PricingPlanService planService;

    @RequestMapping(value = {"/invoice/{invoiceNumber}"}, produces = "application/json")
    public ResponseEntity<?> getNetPayableAmount(@PathVariable(name = "invoiceNumber") String invoiceNumber) {

        Bucket bucket = planService.resolveBucket(invoiceNumber);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            Optional<InvoiceReponse> netPayable = invoiceService.getNetPayableAmount(invoiceNumber);
            if (netPayable.isPresent()) {
                return ResponseEntity.ok(netPayable.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invoice not found");
            }
        }
        long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .header("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill))
                .build();
    }

    @RequestMapping(value = {"/invoice/{customerId}/{amount}"}, produces = "application/json")
    public ResponseEntity<?> generateInvoiceForExistingCustomer(@PathVariable(name = "customerId") Long customerId, @PathVariable("amount") double amount) throws Exception {

        Optional<InvoiceReponse> invoice = invoiceService.generateInvoiceForExistingCustomer(customerId, amount);

        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice);
        } else {
            throw new Exception("Invoice not found");
        }

    }

    @RequestMapping(value = {"/customer/{userType}/{amount}"}, produces = "application/json")
    public ResponseEntity<?> generateInvoiceForNewCustomer(@PathVariable(name = "userType") String userType, @PathVariable("amount") double amount) throws Exception {

        Optional<InvoiceReponse> invoice = invoiceService.generateInvoiceNewCustomer(userType, amount);
        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice.get());
        } else {
            throw new Exception("Invoice not found");
        }

    }
}