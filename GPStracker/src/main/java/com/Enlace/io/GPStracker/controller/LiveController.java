package com.Enlace.io.GPStracker.controller;

import com.Enlace.io.GPStracker.dto.LastPosRes;
import com.Enlace.io.GPStracker.dto.ShareRes;
import com.Enlace.io.GPStracker.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LiveController {

    private final TrackingService trackingService;

    /* ---- Owner: get last position ---- */
    @GetMapping("/api/vehicles/{vehicleId}/last")
    public LastPosRes ownerLast(@PathVariable UUID vehicleId) {
        return trackingService.lastByVehicle(vehicleId);
    }

    /* ---- Owner: create share/mirror link ---- */
    @PostMapping("/api/vehicles/{vehicleId}/share")
    public ShareRes createShare(@PathVariable UUID vehicleId,
                                @RequestParam(defaultValue = "0") long minutes) {
        Duration ttl = minutes > 0 ? Duration.ofMinutes(minutes) : null;
        return trackingService.createShare(vehicleId, ttl);
    }

    /* ---- Mirror: get last position by token ---- */
    @GetMapping("/s/{token}/last")
    public LastPosRes shareLast(@PathVariable String token) {
        return trackingService.lastByToken(token);
    }
}
