package org.example.app;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VehicleJsonRepository implements IVehicleRepository
{
    private final JsonFileStorage<Vehicle> storage =
            new JsonFileStorage<>("vehicles.json", new TypeToken<List<Vehicle>>(){}.getType());

    private final List<Vehicle> vehicles;

    public VehicleJsonRepository()
    {
        this.vehicles = new ArrayList<>(storage.load());    // ładujemy dane z pliku json
    }


    @Override
    public List<Vehicle> findAll()
    {
        return new ArrayList<>(vehicles);
    }

    @Override
    public Optional<Vehicle> findByID(String ID)
    {
        return vehicles.stream().filter(v -> v.getID().equals(ID)).findFirst();
    }

    @Override
    public Vehicle save(Vehicle vehicle)
    {
        if (vehicle.getID() == null || vehicle.getID().isBlank())
        {
            vehicle.setID(UUID.randomUUID().toString());
        }
        else
        {
            deleteByID(vehicle.getID());
        }
        vehicles.add(vehicle);
        storage.save(vehicles);
        return vehicle;
    }

    @Override
    public void deleteByID(String ID)
    {
        vehicles.removeIf(v -> v.getID().equals(ID));
        storage.save(vehicles);
    }
}
