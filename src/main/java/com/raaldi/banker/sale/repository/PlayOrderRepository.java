package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.PlayOrder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("playOrderRepository")
public interface PlayOrderRepository extends CrudRepository<PlayOrder, Long> {
}
