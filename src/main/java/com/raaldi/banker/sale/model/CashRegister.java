package com.raaldi.banker.sale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.raaldi.banker.util.CashRegisterState;
import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "CashRegister")
@Table(name = "bk_cash_register")
@NamedQueries({ @NamedQuery(name = "CashRegister.findAll", query = "SELECT c FROM CashRegister c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CashRegister extends AbstractModel {

  private static final long serialVersionUID = 7871256452762586374L;

  @Id
  @SequenceGenerator(name = "bk-cash-register-seq-gen", sequenceName = "bk_cash_register_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bk-cash-register-seq-gen")
  @Column(name = "cash_register_id")
  private long cashRegisterId;

  @NotNull
  @OneToOne(optional = false)
  @JoinColumn(name = "session_id", nullable = false, insertable = true, updatable = false)
  private Session session;

  @Enumerated(EnumType.STRING)
  private CashRegisterState state;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column(name = "opened_date", insertable = false, updatable = true, columnDefinition = "timestamp")
  private LocalDateTime openedDate;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column(name = "closed_date", insertable = false, updatable = true, columnDefinition = "timestamp")
  private LocalDateTime closedDate;

  @Column(name = "opened_amount", insertable = false, updatable = true)
  private BigDecimal openedAmount;

  @Column(name = "closed_amount", insertable = false, updatable = true)
  private BigDecimal closedAmount;

  public void setState(final CashRegisterState state) {
    switch (state) {
      case OPENING:
        this.setCreatedDate(LocalDateTime.now());
        break;
      case OPENED:
        this.setOpenedDate(LocalDateTime.now());
        break;
      case CLOSING:
        this.setModifiedDate(LocalDateTime.now());
        break;
      case CLOSED:
        this.setClosedDate(LocalDateTime.now());
        break;
    }

    this.state = state;
  }

  public void setOpenedDate(final LocalDateTime openedDate) {

    if (openedAmount == null) {
      throw new IllegalArgumentException("CashRegister.openedAmount may not be null when opening the cash register");
    }

    this.openedDate = openedDate;
  }

  public void setClosedDate(final LocalDateTime closedDate) {

    if (this.closedAmount == null) {
      throw new IllegalArgumentException("CashRegister.closedAmount may not be null when closing the cash register");
    }

    this.closedDate = closedDate;
  }

  @PrePersist
  public void onPrePersist() {
    this.setState(CashRegisterState.OPENING);
  }
}
