package com.raaldi.banker.sale.controller.api;

import com.raaldi.banker.sale.model.SessionState;
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
@RequestMapping(value = "session-states")
public class SessionStateRestController {

  @Autowired
  ModelService<SessionState> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<SessionState>> getAll() {
    Iterable<SessionState> sessionStates = service.findAll();
    if (sessionStates.iterator().hasNext()) {
      return new ResponseEntity<Iterable<SessionState>>(sessionStates, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<SessionState>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SessionState> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching SessionState with id %s", id));
    SessionState sessionState = service.findOne(id);
    if (sessionState == null) {
      log.info(String.format("SessionState with id %s not found", id));
      return new ResponseEntity<SessionState>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<SessionState>(sessionState, HttpStatus.OK);
  }

  @RequestMapping(value = "/session-state", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final SessionState sessionState,
      final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating SessionState %s", sessionState.toString()));

    if (service.exists(sessionState)) {
      log.info(String.format("A SessionState with name %s already exist", sessionState.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(sessionState);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(sessionState.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<SessionState> update(@PathVariable("id") final long id,
      @RequestBody final SessionState sessionState) {
    log.info(String.format("Updating SessionState %s", id));

    SessionState currentSessionState = service.findOne(id);

    if (currentSessionState == null) {
      log.info(String.format("SessionState with id %s not found", id));
      return new ResponseEntity<SessionState>(HttpStatus.NOT_FOUND);
    }

    currentSessionState.setName(sessionState.getName());

    /**
     * TODO: Update entity model service
     */
    // userService.updateSessionState(currentSessionState);
    return new ResponseEntity<SessionState>(currentSessionState, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<SessionState> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting SessionState with id %s", id));

    SessionState sessionState = service.findOne(id);
    if (sessionState == null) {
      log.info(String.format("Unable to delete. SessionState with id %s not found", id));
      return new ResponseEntity<SessionState>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteSessionStateById(id);
    return new ResponseEntity<SessionState>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<SessionState> deleteAll() {
    log.info("Deleting All SessionStates");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllSessionStates();
    return new ResponseEntity<SessionState>(HttpStatus.NO_CONTENT);
  }
}
