package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.Payment;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "Payment")
@Repository("paymentRepository")
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
