package com.raaldi.banker.sale.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "BetLine")
@Table(name = "bk_bet_line")
@NamedQueries({ @NamedQuery(name = "BetLine.findAll", query = "SELECT c FROM BetLine c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BetLine extends AbstractModel {

  private static final long serialVersionUID = 3148027909146756391L;

  @Id
  @SequenceGenerator(name = "bk-bet-line-seq-gen", sequenceName = "bk_bet_line_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bk-bet-line-seq-gen")
  @Column(name = "bet_line_id")
  private long betLineId;

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "bet_id", nullable = false)
  private Bet bet;

  @NotNull
  @Column(name = "play_name", nullable = false, insertable = true, updatable = false)
  private String playName;

  @NotEmpty
  @ElementCollection
  @CollectionTable(name = "bk_bet_line_lottery", joinColumns = { @JoinColumn(name = "bet_line_id") })
  private Set<BetLineLottery> lotteries = Collections.emptySet();

  @NotEmpty
  @ElementCollection
  @CollectionTable(name = "bk_bet_line_number", joinColumns = { @JoinColumn(name = "bet_line_id") })
  private List<BetLineNumber> numbers = Collections.emptyList();

  @NotNull
  @Column(name = "amount", insertable = true, updatable = false)
  private BigDecimal amount;

  @NotNull
  @Column(name = "winner", nullable = false, columnDefinition = "boolean default false")
  private boolean winner;

  @NotNull
  @Column(name = "canceled", nullable = false, columnDefinition = "boolean default false")
  private boolean canceled;
}
