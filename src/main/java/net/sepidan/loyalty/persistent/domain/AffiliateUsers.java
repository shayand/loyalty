package net.sepidan.loyalty.persistent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "affiliate_users")
@RequiredArgsConstructor
@NoArgsConstructor
public class AffiliateUsers {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affiliate_seq_gen")
  @SequenceGenerator(name = "affiliate_seq_gen", sequenceName = "affiliate_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @ToString.Exclude
  @NonNull
  private Affiliates affiliate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @ToString.Exclude
  @NonNull
  private Instances instance;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;
}
