package com.raaldi.banker.sale.service;

import com.raaldi.banker.sale.model.SessionState;
import com.raaldi.banker.sale.repository.SessionStateRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Session state service provides access to the session state repository. */
@NoArgsConstructor
@Service("sessionStateService")
@Transactional
public class SessionStateService implements ModelService<SessionState> {

  /** The session state repository. */
  private SessionStateRepository repository;

  @Autowired
  public void setRepository(final SessionStateRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final SessionState model) {
    repository.save(model);
  }

  @Override
  public SessionState findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<SessionState> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final SessionState model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
