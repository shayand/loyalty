package net.sepidan.loyalty.persistent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import net.sepidan.loyalty.constant.AffiliationIndicator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "affiliates")
@RequiredArgsConstructor
@NoArgsConstructor
public class Affiliates {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affiliate_seq_gen")
  @SequenceGenerator(name = "affiliate_seq_gen", sequenceName = "affiliate_seq", allocationSize = 1)
  private Long id;

  @NonNull
  @Column(name = "affiliation_code")
  private String affiliationCode;

  @NonNull
  @Enumerated(EnumType.STRING)
  private AffiliationIndicator indicatorType;

  @Column(nullable = false, name = "indicator_value")
  @NonNull
  private String indicatorValue;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonIgnore
  @ToString.Exclude
  private Affiliates parent;

  @OneToMany(mappedBy = "affiliate", fetch = FetchType.LAZY)
  List<AffiliateUsers> affiliateUsers = new ArrayList<>();

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;
}
