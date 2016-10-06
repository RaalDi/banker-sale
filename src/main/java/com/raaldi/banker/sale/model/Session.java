package com.raaldi.banker.sale.model;

import com.raaldi.banker.util.EnumSessionState;
import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Session")
@NamedQueries({ @NamedQuery(name = "Session.findAll", query = "SELECT c FROM Session c") })
@Data
@EqualsAndHashCode(callSuper = true)
public class Session extends AbstractModel {

  private static final long serialVersionUID = 5729958504572843209L;

  @Id
  @SequenceGenerator(name = "session-seq-gen", sequenceName = "session_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session-seq-gen")
  private long id;

  @NonNull
  @NotNull
  @Column(name = "company_name", nullable = false, insertable = true, updatable = false)
  private String companyName;

  @NonNull
  @NotNull
  @Column(name = "shop_name", nullable = false, insertable = true, updatable = false)
  private String shopName;

  @NonNull
  @NotNull
  @Column(name = "user_name", nullable = false, insertable = true, updatable = false)
  private String username;

  @Enumerated(EnumType.STRING)
  private EnumSessionState state;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "started", insertable = false, updatable = true)
  private Date started;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "ended", insertable = false, updatable = true)
  private Date ended;

  /** Sets the session state. */
  public void setState(final EnumSessionState state) {

    switch (state) {
      case STARTING:
        this.setCreated(new Date());
        break;
      case STARTED:
        this.setStarted(new Date());
        break;
      case ENDING:
        this.setUpdated(new Date());
        break;
      case ENDED:
        this.setEnded(new Date());
        break;
      default:
        // Do Nothing
        break;
    }

    this.state = state;
  }

  public void setStarted(final Date started) {
    this.started = started == null ? null : new Date(started.getTime());
  }

  public Date getStarted() {
    return started == null ? null : new Date(started.getTime());
  }

  public void setEnded(final Date ended) {
    this.ended = ended == null ? null : new Date(ended.getTime());
  }

  public Date getEnded() {
    return ended == null ? null : new Date(ended.getTime());
  }

  @Override
  @PrePersist
  public void onPersist() {
    this.setState(EnumSessionState.STARTING);
  }
}
