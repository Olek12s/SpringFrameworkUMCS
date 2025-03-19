package org.example.app;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.model.Car;
import org.example.model.Motorcycle;
import org.example.model.Vehicle;
import org.example.repositories.VehicleRepository;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {


        String path = "Repozytorium.csv";
        VehicleRepository repository = new VehicleRepository(path);

        ArrayList<Vehicle> vehicles = repository.getVehiclesShallow();

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

        // deep test

        ArrayList<Vehicle> shallow = repository.getVehiclesShallow();
        ArrayList<Vehicle> deep = repository.getVehicles();

        System.out.println(vehicles.get(0));
        deep.get(0).setID(999);
        System.out.println(vehicles.get(0));


        // shallow test

        System.out.println(vehicles.get(1));
        shallow.get(1).setID(999);
        System.out.println(vehicles.get(1));

        // hashcode && equals test

        Vehicle car1 = new Car("Toyota", "Corolla", 2020, 20000);
        Vehicle car2 = new Car("Toyota", "Corolla", 2020, 20000);
        Vehicle motorcycle = new Motorcycle("Yamaha", "MT-07", 2022, 7500, "Naked");

        car1.setID(1);
        car2.setID(1);
        motorcycle.setID(2);

        System.out.println("Czy car1 jest równy car2? " + car1.equals(car2)); // Powinno być true
        System.out.println("Czy car1 jest równy motorcycle? " + car1.equals(motorcycle)); // Powinno być false
    }
}
