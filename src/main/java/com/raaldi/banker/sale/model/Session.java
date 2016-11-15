package com.raaldi.banker.sale.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;

import javax.persistence.Cacheable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bk_session")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Session")
@NamedQueries({ @NamedQuery(name = "Session.findAll", query = "SELECT c FROM Session c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Session extends AbstractModel {

  private static final long serialVersionUID = 5729958504572843209L;

  @Id
  @SequenceGenerator(name = "bk-session-seq-gen", sequenceName = "bk_session_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bk-session-seq-gen")
  @Column(name = "session_id")
  private long sessionId;

  @NotNull
  @Column(name = "company_name", nullable = false, insertable = true, updatable = false)
  private String companyName;

  @NotNull
  @Column(name = "shop_name", nullable = false, insertable = true, updatable = false)
  private String shopName;

  @Enumerated(EnumType.STRING)
  private SessionState state;

  @Column(name = "started_date", insertable = false, updatable = true, columnDefinition = "timestamp")
  private LocalDateTime startedDate;

  @Column(name = "ended_date", insertable = false, updatable = true, columnDefinition = "timestamp")
  private LocalDateTime endedDate;

  /** Sets the session state. */
  public void setState(final SessionState state) {

    switch (state) {
      case STARTING:
        this.setCreatedDate(LocalDateTime.now());
        break;
      case STARTED:
        this.setStartedDate(LocalDateTime.now());
        break;
      case ENDING:
        this.setModifiedDate(LocalDateTime.now());
        break;
      case ENDED:
        this.setEndedDate(LocalDateTime.now());
        break;
      default:
        // Do Nothing
        break;
    }

    this.state = state;
  }

  @PrePersist
  public void onPrePersist() {
    this.setState(SessionState.STARTING);
  }
}
