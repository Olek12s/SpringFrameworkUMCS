package org.example.repositories;

import org.example.model.Car;
import org.example.model.Motorcycle;
import org.example.model.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class VehicleRepository implements IVehicleRepository
{
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    public static int NEXT_ID;

    /*
    public org.example.repositories.VehicleRepository(ArrayList<org.example.model.Vehicle> vehicles)
    {
        for (org.example.model.Vehicle vehicle : vehicles)
        {
            vehicle.setID(NEXT_ID);
            addVehicle(vehicle);
            NEXT_ID++;
        }
    }
    */

    public VehicleRepository(String filePath)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            reader.readLine(); // slip 1st line
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split("; ");
                if (parts.length >= 6)
                {
                    String brand = parts[0];
                    String model = parts[1];
                    int year = Integer.parseInt(parts[2]);
                    int price = Integer.parseInt(parts[3]);
                    boolean isRented = Boolean.parseBoolean(parts[4]);
                    int id = Integer.parseInt(parts[5]);

                    Vehicle vehicle;
                    if (parts.length == 7)
                    {
                        String category = parts[6];
                        vehicle = new Motorcycle(brand, model, year, price, category);
                    }
                    else
                    {
                        vehicle = new Car(brand, model, year, price);
                    }
                    vehicle.setID(id);
                    vehicle.setRented(isRented);
                    vehicles.add(vehicle);
                    NEXT_ID = Math.max(NEXT_ID, id + 1);
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    //private void addVehicle(Vehicle vehicle)
    //{
    //    vehicles.add(vehicle);
    //}


    @Override
    public void rentVehicle(String brand, String model, int year, int price, String category)
    {
        vehicles.stream()
                .filter(v -> v.getCategory() != null && v.getCategory().equals(category))
                .filter(v -> v.getBrand().equals(brand)
                        && v.getModel().equals(model)
                        && v.getYear() == year
                        && v.getPrice() == price)
                .findFirst()
                .ifPresent(v -> v.setRented(true));

        saveRepositoryToCSV();
    }

    @Override
    public void returnVehicle(int ID)
    {
        vehicles.stream()
                .filter(a -> a.getID() == ID)
                .findFirst()
                .ifPresent(vehicle -> vehicle.setRented(false));

        saveRepositoryToCSV();
    }

    public ArrayList<Vehicle> getVehiclesShallow()  // shallow copy
    {
        return vehicles;
    }

    @Override
    public ArrayList<Vehicle> getVehicles() // deep copy
    {
        try
        {
            // serialization
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(vehicles);
            oos.flush();
            oos.close();

            // deserialization & deep copying
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            ArrayList<Vehicle> deepCopy = (ArrayList<Vehicle>) ois.readObject();
            ois.close();
            return deepCopy;
        }
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
            throw new RuntimeException("Error during executing deep copy.", ex);
        }
    }



    @Override
    public void saveRepositoryToCSV()
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Repozytorium.csv"));

            for (Vehicle vehicle : vehicles)
            {
                writer.write(vehicle.toCSV() + "\n");
            }
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
