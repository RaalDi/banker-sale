package com.raaldi.banker.sale.controller.api;

import com.raaldi.banker.sale.model.Bet;
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
public class BetRestController {

  @Autowired
  ModelService<Bet> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<Bet>> getAll() {
    Iterable<Bet> bets = service.findAll();
    if (bets.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Bet>>(bets, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Bet>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Bet> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Bet with id %s", id));
    Bet bet = service.findOne(id);
    if (bet == null) {
      log.info(String.format("Bet with id %s not found", id));
      return new ResponseEntity<Bet>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Bet>(bet, HttpStatus.OK);
  }

  @RequestMapping(value = "/play-order", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Bet bet, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Bet %s", bet.toString()));

    if (service.exists(bet)) {
      log.info(String.format("A Bet with name %s already exist", bet.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(bet);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(bet.getBetId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Bet> update(@PathVariable("id") final long id, @RequestBody final Bet bet) {
    log.info(String.format("Updating Bet %s", id));

    Bet currentBet = service.findOne(id);

    if (currentBet == null) {
      log.info(String.format("Bet with id %s not found", id));
      return new ResponseEntity<Bet>(HttpStatus.NOT_FOUND);
    }

    currentBet.setAmount(bet.getAmount());
    currentBet.setCanceled(bet.isCanceled());
    currentBet.setCashRegister(bet.getCashRegister());
    currentBet.setPaymentType(bet.getPaymentType());
    currentBet.setBetLines(bet.getBetLines());
    currentBet.setCompanyName(bet.getCompanyName());
    currentBet.setShopName(bet.getShopName());
    currentBet.setWinner(bet.isWinner());
    /**
     * TODO: Update entity model service
     */
    // userService.updateBet(currentBet);
    return new ResponseEntity<Bet>(currentBet, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Bet> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Bet with id %s", id));

    Bet bet = service.findOne(id);
    if (bet == null) {
      log.info(String.format("Unable to delete. Bet with id %s not found", id));
      return new ResponseEntity<Bet>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteBetById(id);
    return new ResponseEntity<Bet>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Bet> deleteAll() {
    log.info("Deleting All Bets");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllBets();
    return new ResponseEntity<Bet>(HttpStatus.NO_CONTENT);
  }
}
