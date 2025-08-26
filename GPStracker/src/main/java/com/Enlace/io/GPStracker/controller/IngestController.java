package com.Enlace.io.GPStracker.controller;

import com.Enlace.io.GPStracker.dto.IngestReq;
import com.Enlace.io.GPStracker.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class IngestController {

    private final TrackingService trackingService;

    @PostMapping("/ingest/{apiKey}")
    public ResponseEntity<Void> ingest(@PathVariable String apiKey, @RequestBody IngestReq req) {
        trackingService.ingest(apiKey, req);
        return ResponseEntity.accepted().build(); // 202
    }
}
