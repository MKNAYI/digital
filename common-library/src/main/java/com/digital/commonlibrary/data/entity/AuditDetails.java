package com.digital.commonlibrary.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {"createdAt", "updatedAt"},
    allowGetters = true)
@Setter
public abstract class AuditDetails implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  private Instant createdAt;

  @Column(name = "updated_at", nullable = false)
  @LastModifiedDate
  private Instant updatedAt;
}
