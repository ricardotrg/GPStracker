package com.Enlace.io.GPStracker.repository;

 import com.Enlace.io.GPStracker.entity.Device;
 import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepo extends JpaRepository<Device, UUID> {
    Optional<Device> findByApiKeyAndActiveTrue(String apiKey);
    Optional<Device> findByImeiAndActiveTrue(String imei);
}
