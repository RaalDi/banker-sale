package com.raaldi.banker.sale.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BetLineLottery implements Serializable {

  private static final long serialVersionUID = 4604753242703504870L;

  @NotNull
  @Column(name = "lottery_name", nullable = false, insertable = true, updatable = false)
  private String lotteryName;
}
