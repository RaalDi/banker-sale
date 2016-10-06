package com.raaldi.banker.sale.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@EqualsAndHashCode(callSuper = false)
public class PlayOrderLineNumber implements Serializable {

  private static final long serialVersionUID = -7239652607366102299L;

  @NonNull
  @NotNull
  @Column(name = "played_number", nullable = false, insertable = true, updatable = false)
  private String number;

}
