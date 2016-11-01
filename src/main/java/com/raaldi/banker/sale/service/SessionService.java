package com.raaldi.banker.sale.service;

import com.raaldi.banker.sale.model.Session;
import com.raaldi.banker.sale.repository.SessionRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Session service provides access to the session repository. */
@NoArgsConstructor
@Service("sessionService")
@Transactional
public class SessionService implements ModelService<Session> {

  /** The session repository. */
  private SessionRepository repository;

  @Autowired
  public void setRepository(final SessionRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Session model) {
    repository.save(model);
  }

  @Override
  public Session findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Session> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Session model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
