package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.CashRegister;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("cashRegisterRepository")
public interface CashRegisterRepository extends CrudRepository<CashRegister, Long> {

}
