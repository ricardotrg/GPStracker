package com.Enlace.io.GPStracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;


import java.time.OffsetDateTime;
import java.util.UUID;

// ShareLinkPublic.java
@Entity
@Table(name="share_link_public")
@Getter
@Setter
public class ShareLinkPublic {
  @Id
  private UUID id = UUID.randomUUID();
  @ManyToOne(optional=false) @JoinColumn(name="vehicle_id")
  private Vehicle vehicle;
  @Column(nullable=false, unique=true) private String token;
  private OffsetDateTime expiresAt;
  @Column(name="is_active", nullable=false) private boolean active = true;
  @Column(name="created_at", nullable=false) private OffsetDateTime createdAt = OffsetDateTime.now();
}