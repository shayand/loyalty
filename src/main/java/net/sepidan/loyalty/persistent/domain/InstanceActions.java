package net.sepidan.loyalty.persistent.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import net.sepidan.loyalty.constant.AffiliateActionSlug;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "instance_action", uniqueConstraints = @UniqueConstraint(columnNames = {"tier_id",
    "slug"}))
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class InstanceActions {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instance_action_gen")
  @SequenceGenerator(name = "instance_action_gen", sequenceName = "instance_action_seq",
      allocationSize = 1)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "instance_id")
  @JsonIgnore
  @ToString.Exclude
  @NonNull
  private Instances instance;

  @ManyToOne
  @JoinColumn(name = "tier_id")
  @JsonIgnore
  @ToString.Exclude
  @NonNull
  private Tiers tiers;

  @Column(name = "slug", length = 100)
  @Enumerated(EnumType.STRING)
  @NonNull
  private AffiliateActionSlug slug;

  @Column(name = "description")
  private String description;

  @Column(name = "point_achieved")
  @NonNull
  private int pointAchieved;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;
}
