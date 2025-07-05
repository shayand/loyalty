package net.sepidan.loyalty.persistent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "affiliate_users")
@NoArgsConstructor
@Data
public class AffiliateUsers {

  public AffiliateUsers(Affiliates affiliate, Tiers tier, Instances instance) {
    this.affiliate = affiliate;
    this.tier = tier;
    this.instance = instance;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affiliate_seq_gen")
  @SequenceGenerator(name = "affiliate_seq_gen", sequenceName = "affiliate_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @ToString.Exclude
  private Affiliates affiliate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="tier_id")
  @JsonIgnore
  @ToString.Exclude
  private Tiers tier;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="instance_id")
  @JsonIgnore
  @ToString.Exclude
  private Instances instance;

  @OneToMany(mappedBy = "affiliateUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<AffiliateUserPoints> affiliateUserPointsList = new ArrayList<>();

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;
}
