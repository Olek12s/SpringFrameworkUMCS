package org.example.app;

import org.apache.commons.codec.digest.DigestUtils;
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

        ArrayList<Vehicle> vehicles = vehicleRepository.getVehicles();

        for (Vehicle v : vehicles)
        {
            System.out.println(v.toString());
        }

        ArrayList<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("a");
        for (String s : a)
        {
            String sha256hex = DigestUtils.sha256Hex(s);
            System.out.println(sha256hex);
        }

        UserRepository userRepository = new UserRepository(userPath);
        Authentication auth = new Authentication(userRepository);
        //User user = new User("abc", "def");
        User admin = new User("Admin000", "def");
        //userRepository.save(user);
        userRepository.save(admin);
        //userRepository.removeVehicle(user, vehicleRepository, 1);
        //userRepository.printUserDetails(admin);
        //userRepository.printUserDetails(user);
        userRepository.printAllUsers(admin);
        //userRepository.printAllUsers(user);
        auth.login();
    }
}
