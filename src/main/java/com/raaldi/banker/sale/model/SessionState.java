package com.raaldi.banker.sale.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "SessionState")
@Table(name = "session_state")
@NamedQueries({ @NamedQuery(name = "SessionState.findAll", query = "SELECT c FROM SessionState c") })
@Data
@EqualsAndHashCode(callSuper = true)
public class SessionState extends AbstractModel {

  private static final long serialVersionUID = 6611770901282246338L;

  @Id
  @SequenceGenerator(name = "session-state-seq-gen", sequenceName = "session_state_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session-state-seq-gen")
  private long id;

  @NonNull
  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  private String name;

}
