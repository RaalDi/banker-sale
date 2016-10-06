package com.raaldi.banker.sale.repository;

import com.raaldi.banker.sale.model.SessionState;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("sessionStateRepository")
public interface SessionStateRepository extends CrudRepository<SessionState, Long> {
}
