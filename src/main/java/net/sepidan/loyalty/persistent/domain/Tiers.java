package net.sepidan.loyalty.persistent.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.ZonedDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.sepidan.loyalty.constant.TierSlug;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "tiers",
    uniqueConstraints = @UniqueConstraint(columnNames = {"tier_slug", "level", "deleted_at"})
)
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Tiers {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_tiers_seq_gen")
  @SequenceGenerator(name = "instance_tiers_seq_gen", sequenceName = "instance_tiers_seq", allocationSize = 1)
  private Long id;

  @Column(name = "tier_slug")
  @Enumerated(EnumType.STRING)
  @NonNull
  private TierSlug tierSlug;

  @Column(name = "title", length = 100)
  @NonNull
  private String title;

  @Column(name = "level")
  @NonNull
  private int level;

  @Column(name = "points_threshold")
  @NonNull
  private int pointsThreshold;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;
}
