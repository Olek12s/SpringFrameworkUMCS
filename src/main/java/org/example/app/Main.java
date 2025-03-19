package org.example.app;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.model.Vehicle;
import org.example.repositories.VehicleRepository;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {


        String path = "Repozytorium.csv";
        VehicleRepository repository = new VehicleRepository(path);

        ArrayList<Vehicle> vehicles = repository.getVehicles();

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
    }
}
