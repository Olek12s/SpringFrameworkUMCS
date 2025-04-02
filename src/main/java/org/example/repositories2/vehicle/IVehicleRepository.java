package org.example.repositories2.vehicle;

import org.example.model2.Vehicle;

import java.util.ArrayList;

public interface IVehicleRepository
{
    void rentVehicle(String brand, String model, int year, int price, String category);
    void getVehicle(int ID);
    ArrayList<Vehicle> getVehicles();   // deep copy
    void saveRepositoryToCSV();    // zapisywanie do CSV
    void addVehicle(Vehicle vehicle);
    void removeVehicle(int ID);
}
