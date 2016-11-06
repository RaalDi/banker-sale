package com.raaldi.banker.sale.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "PlayOrder")
@Table(name = "play_order")
@NamedQueries({ @NamedQuery(name = "PlayOrder.findAll", query = "SELECT c FROM PlayOrder c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayOrder extends AbstractModel {

  private static final long serialVersionUID = -2831284612290806696L;

  @Id
  @SequenceGenerator(name = "play-order-seq-gen", sequenceName = "play_order_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play-order-seq-gen")
  @Column(name = "play_order_id")
  private long playOrderId;

  @NotNull
  @Column(name = "company_name", nullable = false, insertable = true, updatable = false)
  private String companyName;

  @NotNull
  @Column(name = "shop_name", nullable = false, insertable = true, updatable = false)
  private String shopName;

  @NotNull
  @Column(name = "amount", nullable = false, insertable = true, updatable = false)
  private BigDecimal amount;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "payment_id", nullable = false, updatable = false)
  private Payment payment;

  @NotNull
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cash_register_id", nullable = false, updatable = false)
  private CashRegister cashRegister;

  @NotNull
  @Column(name = "winner", nullable = false, columnDefinition = "boolean default false")
  private boolean winner;

  @NotNull
  @Column(name = "canceled", nullable = false, columnDefinition = "boolean default false")
  private boolean canceled;

  @NotNull
  @OneToMany(mappedBy = "playOrder", cascade = CascadeType.ALL)
  private List<PlayOrderLine> playOrderLines = Collections.emptyList();
}
