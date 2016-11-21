package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.PaymentType;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "PaymentType")
@Repository("paymentTypeRepository")
public interface PaymentTypeRepository extends CrudRepository<PaymentType, Long> {
}
