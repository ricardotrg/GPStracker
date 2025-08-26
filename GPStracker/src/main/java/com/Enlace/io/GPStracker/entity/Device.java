package com.Enlace.io.GPStracker.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

// Device.java
@Entity
@Table(name="device")
@Getter
@Setter
public class Device {
  @Id
  private UUID id = UUID.randomUUID();
  @ManyToOne(optional=false) @JoinColumn(name="vehicle_id")
  private Vehicle vehicle;
  @Column(nullable=false, unique=true) private String imei;
  @Column(name="api_key", nullable=false, unique=true) private String apiKey;
  @Column(name="created_at", nullable=false) private OffsetDateTime createdAt = OffsetDateTime.now();
  @Column(name="is_active", nullable=false) private boolean active = true;
}
