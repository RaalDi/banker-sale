package com.raaldi.banker.sale.controller.api;

import com.raaldi.banker.sale.model.Payment;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
/** Address service provides access to the address repository. */
@NoArgsConstructor
@RestController
@RequestMapping(value = "payments")
public class PaymentRestController {

  @Autowired
  ModelService<Payment> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Payment>> getAll() {
    Iterable<Payment> payments = service.findAll();
    if (payments.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Payment>>(payments, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Payment>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Payment> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Payment with id %s", id));
    Payment payment = service.findOne(id);
    if (payment == null) {
      log.info(String.format("Payment with id %s not found", id));
      return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Payment>(payment, HttpStatus.OK);
  }

  @RequestMapping(value = "/payments", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Payment payment, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Payment %s", payment.toString()));

    if (service.exists(payment)) {
      log.info(String.format("A Payment with name %s already exist", payment.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(payment);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(payment.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Payment> update(@PathVariable("id") final long id, @RequestBody final Payment payment) {
    log.info(String.format("Updating Payment %s", id));

    Payment currentPayment = service.findOne(id);

    if (currentPayment == null) {
      log.info(String.format("Payment with id %s not found", id));
      return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }

    currentPayment.setType(payment.getType());
    /**
     * TODO: Update entity model service
     */
    // userService.updatePayment(currentPayment);
    return new ResponseEntity<Payment>(currentPayment, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Payment> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Payment with id %s", id));

    Payment payment = service.findOne(id);
    if (payment == null) {
      log.info(String.format("Unable to delete. Payment with id %s not found", id));
      return new ResponseEntity<Payment>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deletePaymentById(id);
    return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Payment> deleteAll() {
    log.info("Deleting All Payments");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllPayments();
    return new ResponseEntity<Payment>(HttpStatus.NO_CONTENT);
  }
}
