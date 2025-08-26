package com.Enlace.io.GPStracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ShareRes {
    private String url;
    private String token;
    private OffsetDateTime expiresAt;
}
