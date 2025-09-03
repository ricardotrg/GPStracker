package com.Enlace.io.GPStracker.controller;

import com.Enlace.io.GPStracker.dto.VehicleOption;
import com.Enlace.io.GPStracker.entity.Vehicle;
import com.Enlace.io.GPStracker.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VehiclesController {
  private final VehicleRepo vehicleRepo;

  @GetMapping("/api/vehicles")
  public List<VehicleOption> list() {
    return vehicleRepo.findAll().stream()
        .map(v -> new VehicleOption(v.getId(), v.getName(), v.getPlate()))
        .toList();
  }
}
