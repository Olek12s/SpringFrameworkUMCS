package org.example.repositories;

import org.example.model.Vehicle;

import java.util.ArrayList;

public interface IVehicleRepository
{
    void rentVehicle(String brand, String model, int year, int price, String category);
    void returnVehicle(int ID);
    ArrayList<Vehicle> getVehicles();   // deep copy
    void saveRepositoryToCSV();    // zapisywanie do CSV
}
