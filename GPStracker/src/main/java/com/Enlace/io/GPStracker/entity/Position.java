package com.Enlace.io.GPStracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;


import java.time.OffsetDateTime;

// Position.java
@Entity
@Table(name="position")
@Getter
@Setter
public class Position {
  @Id @GeneratedValue(strategy= GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) @JoinColumn(name="vehicle_id")
  private Vehicle vehicle;
  @Column(name="fix_time", nullable=false) private OffsetDateTime fixTime;
  @Column(nullable=false) private double lat;
  @Column(nullable=false) private double lon;
  private Double speedKph;
  private Double headingDeg;
}
