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
import net.sepidan.loyalty.constant.TierSlug;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(
    name = "tiers",
    uniqueConstraints = @UniqueConstraint(columnNames = {"tier_slug", "level", "deleted_at"})
)
@NoArgsConstructor
@Data
public class Tiers {

  public Tiers(TierSlug tierSlug, String title, int level, int pointsThreshold) {
    this.tierSlug = tierSlug;
    this.title = title;
    this.level = level;
    this.pointsThreshold = pointsThreshold;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_tiers_seq_gen")
  @SequenceGenerator(name = "instance_tiers_seq_gen", sequenceName = "instance_tiers_seq", allocationSize = 1)
  private Long id;

  @Column(name = "tier_slug")
  @Enumerated(EnumType.STRING)
  private TierSlug tierSlug;

  @Column(name = "title", length = 100)
  private String title;

  @Column(name = "level")
  private int level;

  @Column(name = "points_threshold")
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
