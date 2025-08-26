package com.Enlace.io.GPStracker.repository;

import com.Enlace.io.GPStracker.entity.Position;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PositionRepo extends JpaRepository<Position, Long> {
    // Última posición (owner / espejo)
    Optional<Position> findFirstByVehicle_IdOrderByFixTimeDesc(UUID vehicleId);

    // Historial reciente (paginado por tiempo desc)
    List<Position> findByVehicle_IdAndFixTimeBeforeOrderByFixTimeDesc(UUID vehicleId,
                                                                      OffsetDateTime before,
                                                                      Pageable page);

    // Para limpiar o métricas rápidas
    long countByVehicle_Id(UUID vehicleId);
}
