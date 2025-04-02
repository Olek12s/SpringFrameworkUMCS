package org.example.app;

import java.util.List;
import java.util.Optional;

public interface IVehicleRepository
{
    List<Vehicle> findAll();
    Optional<Vehicle> findByID(String ID);
    Vehicle save(Vehicle vehicle);
    void deleteByID(String ID);
}
