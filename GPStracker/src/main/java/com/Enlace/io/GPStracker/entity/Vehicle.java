package com.Enlace.io.GPStracker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="vehicle")
@Getter
@Setter
public class Vehicle {
  @Id
  private UUID id = UUID.randomUUID();
  private String name;
  private String plate;
  @Column(unique=true) private String imei;
  @Column(name="created_at") private OffsetDateTime createdAt;
  @Column(name="is_active") private boolean active;
}
