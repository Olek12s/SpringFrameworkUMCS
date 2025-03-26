package org.example.app;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.model.Car;
import org.example.model.Vehicle;
import org.example.repositories.user.User;
import org.example.repositories.user.UserRepository;
import org.example.repositories.vehicle.VehicleRepository;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {


        String vehiclePath = "RepozytoriumPojazdow.csv";
        String userPath = "RepozytoriumUzytkownikow.csv";
        VehicleRepository vehicleRepository = new VehicleRepository(vehiclePath);
        Vehicle car = new Car("brand-0", "model-x", 2025, 9999);
        vehicleRepository.addVehicle(car);

        ArrayList<Vehicle> vehicles = vehicleRepository.getVehicles();

        for (Vehicle v : vehicles)
        {
            System.out.println(v.toString());
        }

        UserRepository userRepository = new UserRepository(userPath);
        Authentication auth = new Authentication(userRepository);
        User user = new User("abc", "def", "user", -1, true);
        User admin = new User("Admin000", "admin", "admin", -1, false);
        userRepository.save(user, userPath);
        userRepository.save(admin, userPath);
        userRepository.removeVehicle(user, vehicleRepository, 1);
        userRepository.removeVehicle(admin, vehicleRepository, 12);
        //userRepository.printUserDetails(admin);
        //userRepository.printUserDetails(user);
        //userRepository.printAllUsers(admin);
        userRepository.printAllUsers(user);
        auth.login();
    }
}
