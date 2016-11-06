package com.raaldi.banker.sale.service;

import com.raaldi.banker.sale.model.CashRegister;
import com.raaldi.banker.sale.repository.CashRegisterRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Cash register service provides access to the cash register repository. */
@NoArgsConstructor
@Service("cashRegisterService")
@Transactional
public class CashRegisterService implements ModelService<CashRegister> {

  /** The cash register repository. */
  private CashRegisterRepository repository;

  @Autowired
  public void setRepository(final CashRegisterRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final CashRegister model) {
    repository.save(model);
  }

  @Override
  public CashRegister findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<CashRegister> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final CashRegister model) {
    return this.exists(model.getCashRegisterId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
