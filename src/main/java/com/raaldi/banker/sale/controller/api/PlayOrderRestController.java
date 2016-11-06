package com.raaldi.banker.sale.controller.api;

import com.raaldi.banker.sale.model.PlayOrder;
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
@RequestMapping(value = "play-orders")
public class PlayOrderRestController {

  @Autowired
  ModelService<PlayOrder> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<PlayOrder>> getAll() {
    Iterable<PlayOrder> playOrders = service.findAll();
    if (playOrders.iterator().hasNext()) {
      return new ResponseEntity<Iterable<PlayOrder>>(playOrders, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<PlayOrder>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PlayOrder> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching PlayOrder with id %s", id));
    PlayOrder playOrder = service.findOne(id);
    if (playOrder == null) {
      log.info(String.format("PlayOrder with id %s not found", id));
      return new ResponseEntity<PlayOrder>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<PlayOrder>(playOrder, HttpStatus.OK);
  }

  @RequestMapping(value = "/play-order", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final PlayOrder playOrder, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating PlayOrder %s", playOrder.toString()));

    if (service.exists(playOrder)) {
      log.info(String.format("A PlayOrder with name %s already exist", playOrder.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(playOrder);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(playOrder.getPlayOrderId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<PlayOrder> update(@PathVariable("id") final long id, @RequestBody final PlayOrder playOrder) {
    log.info(String.format("Updating PlayOrder %s", id));

    PlayOrder currentPlayOrder = service.findOne(id);

    if (currentPlayOrder == null) {
      log.info(String.format("PlayOrder with id %s not found", id));
      return new ResponseEntity<PlayOrder>(HttpStatus.NOT_FOUND);
    }

    currentPlayOrder.setAmount(playOrder.getAmount());
    currentPlayOrder.setCanceled(playOrder.isCanceled());
    currentPlayOrder.setCashRegister(playOrder.getCashRegister());
    currentPlayOrder.setPayment(playOrder.getPayment());
    currentPlayOrder.setPlayOrderLines(playOrder.getPlayOrderLines());
    currentPlayOrder.setCompanyName(playOrder.getCompanyName());
    currentPlayOrder.setShopName(playOrder.getShopName());
    currentPlayOrder.setWinner(playOrder.isWinner());
    /**
     * TODO: Update entity model service
     */
    // userService.updatePlayOrder(currentPlayOrder);
    return new ResponseEntity<PlayOrder>(currentPlayOrder, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<PlayOrder> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting PlayOrder with id %s", id));

    PlayOrder playOrder = service.findOne(id);
    if (playOrder == null) {
      log.info(String.format("Unable to delete. PlayOrder with id %s not found", id));
      return new ResponseEntity<PlayOrder>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deletePlayOrderById(id);
    return new ResponseEntity<PlayOrder>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<PlayOrder> deleteAll() {
    log.info("Deleting All PlayOrders");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllPlayOrders();
    return new ResponseEntity<PlayOrder>(HttpStatus.NO_CONTENT);
  }
}
