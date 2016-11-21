package com.raaldi.banker.sale.controller.api;

import com.raaldi.banker.sale.model.PaymentType;
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
@RequestMapping(value = "payment-types")
public class PaymentTypeRestController {

  @Autowired
  ModelService<PaymentType> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<PaymentType>> getAll() {
    Iterable<PaymentType> paymentTypes = service.findAll();
    if (paymentTypes.iterator().hasNext()) {
      return new ResponseEntity<Iterable<PaymentType>>(paymentTypes, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<PaymentType>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PaymentType> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching PaymenType with id %s", id));
    PaymentType paymentType = service.findOne(id);
    if (paymentType == null) {
      log.info(String.format("PaymenType with id %s not found", id));
      return new ResponseEntity<PaymentType>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<PaymentType>(paymentType, HttpStatus.OK);
  }

  @RequestMapping(value = "/payment-type", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final PaymentType paymentType,
      final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating PaymenType %s", paymentType.toString()));

    if (service.exists(paymentType)) {
      log.info(String.format("A PaymenType with name %s already exist", paymentType.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(paymentType);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(paymentType.getPaymentTypeId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<PaymentType> update(@PathVariable("id") final long id,
      @RequestBody final PaymentType paymentType) {
    log.info(String.format("Updating PaymenType %s", id));

    PaymentType currentPaymenType = service.findOne(id);

    if (currentPaymenType == null) {
      log.info(String.format("PaymenType with id %s not found", id));
      return new ResponseEntity<PaymentType>(HttpStatus.NOT_FOUND);
    }

    currentPaymenType.setType(paymentType.getType());
    /**
     * TODO: Update entity model service
     */
    // userService.updatePaymenType(currentPaymenType);
    return new ResponseEntity<PaymentType>(currentPaymenType, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PaymentType> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting PaymenType with id %s", id));

    PaymentType paymentType = service.findOne(id);
    if (paymentType == null) {
      log.info(String.format("Unable to delete. PaymenType with id %s not found", id));
      return new ResponseEntity<PaymentType>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deletePaymenTypeById(id);
    return new ResponseEntity<PaymentType>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<PaymentType> deleteAll() {
    log.info("Deleting All PaymenTypes");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllPaymenTypes();
    return new ResponseEntity<PaymentType>(HttpStatus.NO_CONTENT);
  }
}
