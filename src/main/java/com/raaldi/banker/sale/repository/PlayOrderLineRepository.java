package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.PlayOrderLine;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "PlayOrderLine")
@Repository("playOrderLineRepository")
public interface PlayOrderLineRepository extends CrudRepository<PlayOrderLine, Long> {
}
