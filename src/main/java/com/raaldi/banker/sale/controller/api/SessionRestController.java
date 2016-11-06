package com.raaldi.banker.sale.controller.api;

import com.raaldi.banker.sale.model.Session;
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
@RequestMapping(value = "sessions")
public class SessionRestController {

  @Autowired
  ModelService<Session> service;

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<Session>> getAll() {
    Iterable<Session> sessions = service.findAll();
    if (sessions.iterator().hasNext()) {
      return new ResponseEntity<Iterable<Session>>(sessions, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<Session>>(sessions, HttpStatus.NOT_FOUND);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Session> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching Session with id %s", id));
    Session session = service.findOne(id);
    if (session == null) {
      log.info(String.format("Session with id %s not found", id));
      return new ResponseEntity<Session>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Session>(session, HttpStatus.OK);
  }

  @RequestMapping(value = "/session", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final Session session, final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating Session %s", session.toString()));

    if (service.exists(session)) {
      log.info(String.format("A Session with name %s already exist", session.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(session);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(session.getSessionId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Session> update(@PathVariable("id") final long id, @RequestBody final Session session) {
    log.info(String.format("Updating Session %s", id));

    Session currentSession = service.findOne(id);

    if (currentSession == null) {
      log.info(String.format("Session with id %s not found", id));
      return new ResponseEntity<Session>(HttpStatus.NOT_FOUND);
    }

    currentSession.setEndedDate(session.getEndedDate());
    currentSession.setStartedDate(session.getStartedDate());
    currentSession.setState(session.getState());
    currentSession.setCompanyName(session.getCompanyName());
    currentSession.setShopName(session.getShopName());
    /**
     * TODO: Update entity model service
     */
    // userService.updateSession(currentSession);
    return new ResponseEntity<Session>(currentSession, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Session> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting Session with id %s", id));

    Session session = service.findOne(id);
    if (session == null) {
      log.info(String.format("Unable to delete. Session with id %s not found", id));
      return new ResponseEntity<Session>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteSessionById(id);
    return new ResponseEntity<Session>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<Session> deleteAll() {
    log.info("Deleting All Sessions");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllSessions();
    return new ResponseEntity<Session>(HttpStatus.NO_CONTENT);
  }
}
