package com.raaldi.banker.sale.service;

import com.raaldi.banker.sale.model.PlayOrder;
import com.raaldi.banker.sale.repository.PlayOrderRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Play order service provides access to the play order repository. */
@NoArgsConstructor
@Service("playOrderService")
@Transactional
public class PlayOrderService implements ModelService<PlayOrder> {

  /** The play order repository. */
  private PlayOrderRepository repository;

  @Autowired
  public void setRepository(final PlayOrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final PlayOrder model) {
    repository.save(model);
  }

  @Override
  public PlayOrder findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<PlayOrder> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final PlayOrder model) {
    return this.exists(model.getPlayOrderId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
