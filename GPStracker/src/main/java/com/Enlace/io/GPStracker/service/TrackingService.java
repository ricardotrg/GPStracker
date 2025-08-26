package com.Enlace.io.GPStracker.service;

import com.Enlace.io.GPStracker.dto.IngestReq;
import com.Enlace.io.GPStracker.dto.LastPosRes;
import com.Enlace.io.GPStracker.dto.ShareRes;
import com.Enlace.io.GPStracker.entity.Device;
import com.Enlace.io.GPStracker.entity.Position;
import com.Enlace.io.GPStracker.entity.ShareLinkPublic;
import com.Enlace.io.GPStracker.entity.Vehicle;
import com.Enlace.io.GPStracker.repository.DeviceRepo;
import com.Enlace.io.GPStracker.repository.PositionRepo;
import com.Enlace.io.GPStracker.repository.ShareLinkPublicRepo;
import com.Enlace.io.GPStracker.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final DeviceRepo deviceRepo;
    private final VehicleRepo vehicleRepo;
    private final PositionRepo positionRepo;
    private final ShareLinkPublicRepo shareRepo;

    /* ---- INGEST ---- */
    @Transactional
    public void ingest(String apiKey, IngestReq req) {
        Device d = deviceRepo.findByApiKeyAndActiveTrue(apiKey)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid apiKey"));

        Position p = new Position();
        p.setVehicle(d.getVehicle());
        p.setFixTime(Optional.ofNullable(req.getFixTime()).orElse(OffsetDateTime.now()));
        p.setLat(req.getLat());
        p.setLon(req.getLon());
        p.setSpeedKph(req.getSpeedKph());
        p.setHeadingDeg(req.getHeadingDeg());
        positionRepo.save(p);
    }

    /* ---- LAST (owner) ---- */
    @Transactional(readOnly = true)
    public LastPosRes lastByVehicle(UUID vehicleId) {
        Position p = positionRepo.findFirstByVehicle_IdOrderByFixTimeDesc(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no position"));
        return new LastPosRes(
                vehicleId,
                p.getFixTime(),
                p.getLat(),
                p.getLon(),
                p.getSpeedKph(),
                p.getHeadingDeg()
        );
    }

    /* ---- SHARE (create mirror link) ---- */
    @Transactional
    public ShareRes createShare(UUID vehicleId, Duration ttl) {
        // getReferenceById avoids full fetch; throws on missing id at flush time.
        Vehicle ref = vehicleRepo.getReferenceById(vehicleId);

        ShareLinkPublic s = new ShareLinkPublic();
        s.setId(UUID.randomUUID());
        s.setVehicle(ref);
        s.setToken(randomToken());
        s.setExpiresAt(ttl != null ? OffsetDateTime.now().plus(ttl) : null);
        s.setActive(true);
        shareRepo.save(s);

        return new ShareRes("/s/" + s.getToken(), s.getToken(), s.getExpiresAt());
    }

    /* ---- LAST (mirror) ---- */
    @Transactional(readOnly = true)
    public LastPosRes lastByToken(String token) {
        ShareLinkPublic s = shareRepo.findByTokenAndActiveTrue(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bad token"));

        if (s.getExpiresAt() != null && s.getExpiresAt().isBefore(OffsetDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.GONE, "expired");
        }
        return lastByVehicle(s.getVehicle().getId());
    }

    /* ---- token generator ---- */
    private static final SecureRandom RNG = new SecureRandom();
    private static String randomToken() {
        // 128-bit token hex (32 chars)
        byte[] b = new byte[16];
        RNG.nextBytes(b);
        StringBuilder sb = new StringBuilder(32);
        for (byte x : b) sb.append(String.format("%02x", x));
        return sb.toString();
    }
}
