package com.Enlace.io.GPStracker.dto;

import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class IngestReq {
    private double lat;
    private double lon;
    private OffsetDateTime fixTime;
    private Double speedKph;
    private Double headingDeg;
}
