package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.BetLine;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "BetLine")
@Repository("betLineRepository")
public interface BetLineRepository extends CrudRepository<BetLine, Long> {
}
