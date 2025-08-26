package com.Enlace.io.GPStracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class LastPosRes {
    private UUID vehicleId;
    private OffsetDateTime fixTime;
    private double lat;
    private double lon;
    private Double speedKph;
    private Double headingDeg;
}
