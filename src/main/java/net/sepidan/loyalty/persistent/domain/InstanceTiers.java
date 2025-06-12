package net.sepidan.loyalty.persistent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    name = "instance_tiers",
    uniqueConstraints = @UniqueConstraint(columnNames = {"instance_id", "level", "deleted_at"})
)
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class InstanceTiers {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_tiers_seq_gen")
  @SequenceGenerator(name = "instance_tiers_seq_gen", sequenceName = "instance_tiers_seq", allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "instance_id")
  @JsonIgnore
  @ToString.Exclude
  private Instances instance;

  @Column(name = "title", length = 100)
  @NonNull
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
