package com.Enlace.io.GPStracker.repository;

import com.Enlace.io.GPStracker.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepo extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByImei(String imei);
    boolean existsByIdAndActiveTrue(UUID id);
}
