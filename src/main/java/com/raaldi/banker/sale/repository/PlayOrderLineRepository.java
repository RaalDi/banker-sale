package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.PlayOrderLine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("playOrderLineRepository")
public interface PlayOrderLineRepository extends CrudRepository<PlayOrderLine, Long> {
}
