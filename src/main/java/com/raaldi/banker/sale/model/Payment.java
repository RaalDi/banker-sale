package com.raaldi.banker.sale.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Payment")
@NamedQueries({ @NamedQuery(name = "Payment.findAll", query = "SELECT c FROM Payment c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Payment extends AbstractModel {

  private static final long serialVersionUID = 1017925264098582407L;

  @Id
  @SequenceGenerator(name = "payment-seq-gen", sequenceName = "payment_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment-seq-gen")
  @Column(name = "payment_id")
  private long paymentId;

  @NotNull
  @Column(name = "type", nullable = false, unique = true)
  private String type;

  @NotNull
  @Column(name = "active", nullable = false, columnDefinition = "boolean default false")
  private boolean active;
}
