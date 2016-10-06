package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.Session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public interface SessionRepository extends CrudRepository<Session, Long> {
}
