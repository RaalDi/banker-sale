package com.raaldi.banker.sale.service;

import com.raaldi.banker.sale.model.Bet;
import com.raaldi.banker.sale.repository.BetRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Play order service provides access to the play order repository. */
@NoArgsConstructor
@Service("playOrderService")
@Transactional
public class BetService implements ModelService<Bet> {

  /** The play order repository. */
  private BetRepository repository;

  @Autowired
  public void setRepository(final BetRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Bet model) {
    repository.save(model);
  }

  @Override
  public Bet findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Bet> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Bet model) {
    return this.exists(model.getBetId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
