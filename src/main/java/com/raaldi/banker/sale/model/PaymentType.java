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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bk_payment_type")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "PaymentType")
@NamedQueries({ @NamedQuery(name = "PaymentType.findAll", query = "SELECT c FROM PaymentType c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentType extends AbstractModel {

  private static final long serialVersionUID = 1017925264098582407L;

  @Id
  @SequenceGenerator(name = "bk-payment-type-seq-gen", sequenceName = "bk_payment_type_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bk-payment-type-seq-gen")
  @Column(name = "payment_type_id")
  private long paymentTypeId;

  @NotNull
  @Column(name = "type", nullable = false, unique = true)
  private String type;

  @NotNull
  @Column(name = "active", nullable = false, columnDefinition = "boolean default false")
  private boolean active;
}
