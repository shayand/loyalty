package net.sepidan.loyalty.persistent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "affliate_user_points",
    uniqueConstraints = @UniqueConstraint(columnNames = {"affiliate_user_id", "action_id",
        "deleted_at"})
)
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class AffiliateUserPoints {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "affiliate_user_points_gen")
  @SequenceGenerator(name = "affiliate_user_points_gen", sequenceName = "affiliate_user_points_seq",
      allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "affiliate_user_id",nullable = false)
  @JsonIgnore
  @ToString.Exclude
  @NonNull
  private AffiliateUsers affiliateUser;

  @ManyToOne
  @JoinColumn(name = "action_id")
  @JsonIgnore
  @ToString.Exclude
  @NonNull
  private InstanceActions action;

  @Column(name = "point")
  private int point;


  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;
}
