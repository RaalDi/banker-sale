package com.raaldi.banker.sale.service;

import com.raaldi.banker.sale.model.PaymentType;
import com.raaldi.banker.sale.repository.PaymentTypeRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Payment service provides access to the payment repository. */
@NoArgsConstructor
@Service("paymentTypeService")
@Transactional
public class PaymentTypeService implements ModelService<PaymentType> {

  /** The payment repository. */
  private PaymentTypeRepository repository;

  @Autowired
  public void setRepository(final PaymentTypeRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final PaymentType model) {
    repository.save(model);
  }

  @Override
  public PaymentType findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<PaymentType> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final PaymentType model) {
    return this.exists(model.getPaymentTypeId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
