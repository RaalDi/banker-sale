package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.Session;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "Session")
@Repository("sessionRepository")
public interface SessionRepository extends CrudRepository<Session, Long> {
}
