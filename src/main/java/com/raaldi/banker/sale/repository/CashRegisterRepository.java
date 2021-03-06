package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.CashRegister;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "CashRegister")
@Repository("cashRegisterRepository")
public interface CashRegisterRepository extends CrudRepository<CashRegister, Long> {

}
