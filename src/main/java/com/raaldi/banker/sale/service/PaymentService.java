package com.raaldi.banker.sale.service;

import com.raaldi.banker.sale.model.Payment;
import com.raaldi.banker.sale.repository.PaymentRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Payment service provides access to the payment repository. */
@NoArgsConstructor
@Service("paymentService")
@Transactional
public class PaymentService implements ModelService<Payment> {

  /** The payment repository. */
  private PaymentRepository repository;

  @Autowired
  public void setRepository(final PaymentRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final Payment model) {
    repository.save(model);
  }

  @Override
  public Payment findOne(final Long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<Payment> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final Payment model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final Long id) {
    return repository.exists(id);
  }

}
