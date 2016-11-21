package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.Bet;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "Bet")
@Repository("betRepository")
public interface BetRepository extends CrudRepository<Bet, Long> {
}
