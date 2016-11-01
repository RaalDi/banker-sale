package com.raaldi.banker.sale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.raaldi.banker.util.EnumSessionState;
import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Session")
@NamedQueries({ @NamedQuery(name = "Session.findAll", query = "SELECT c FROM Session c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Session extends AbstractModel {

  private static final long serialVersionUID = 5729958504572843209L;

  @Id
  @SequenceGenerator(name = "session-seq-gen", sequenceName = "session_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session-seq-gen")
  private long id;

  @NotNull
  @Column(name = "company_name", nullable = false, insertable = true, updatable = false)
  private String companyName;

  @NotNull
  @Column(name = "shop_name", nullable = false, insertable = true, updatable = false)
  private String shopName;

  @NotNull
  @Column(name = "user_name", nullable = false, insertable = true, updatable = false)
  private String username;

  @Enumerated(EnumType.STRING)
  private EnumSessionState state;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column(name = "started", insertable = false, updatable = true, columnDefinition = "timestamp")
  private LocalDateTime started;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Column(name = "ended", insertable = false, updatable = true, columnDefinition = "timestamp")
  private LocalDateTime ended;

  /** Sets the session state. */
  public void setState(final EnumSessionState state) {

    switch (state) {
      case STARTING:
        this.setCreated(LocalDateTime.now());
        break;
      case STARTED:
        this.setStarted(LocalDateTime.now());
        break;
      case ENDING:
        this.setUpdated(LocalDateTime.now());
        break;
      case ENDED:
        this.setEnded(LocalDateTime.now());
        break;
      default:
        // Do Nothing
        break;
    }

    this.state = state;
  }

  @Override
  @PrePersist
  public void onPersist() {
    this.setState(EnumSessionState.STARTING);
  }
}
