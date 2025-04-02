package org.example.app;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {

        UserJsonRepository userRepository = new UserJsonRepository();
        VehicleJsonRepository vehicleRepository = new VehicleJsonRepository();
        RentalJsonRepository rentalRepository = new RentalJsonRepository();


       // AuthService authService = new AuthService(userRepository);
       // VehicleService vehicleService = new VehicleService(vehicleRepository, rentalRepository);
       // RentalService rentalService = new RentalService(rentalRepository, vehicleRepository);

       // User admin = authService.register("admin", "admin123");     // ok o ile nie było go w bazie
       // User user = authService.register("john", "john123");        // ok o ile nie było go w bazie
       // User loggedUser = authService.login("john", "john123");     // ok o ile był w bazie
       // User loggedAdmin = authService.login("admin", "admin123");  // ok o ile był w bazie

       // vehicleService.addVehicle(new Vehicle("Audi A4"), admin);   // ok, jeśli takiego auta nie ma
       // vehicleService.addVehicle(new Vehicle("Audi A4"), user);   // user nie może dodawać
       // rentalService.rentVehicle(1, loggedUser);
       // rentalService.rentVehicle(1, loggedUser);   // Błąd - to auto już jest wypożyczone i użytkownik już wypożyczył




        Map<String, Object> attributes = new HashMap<>();
        attributes.put(null, null);
        Vehicle vehicle = Vehicle.builder()
                .brand("Volvo")
                .model("V50")
                .year(2000)
                .plate("LU000")
                .category("Car")
                .attributes(attributes)
                .build();


        JsonFileStorage<Vehicle> jsf = new JsonFileStorage<>(
                "test.json",
                new TypeToken<List<Vehicle>>() {}.getType()
        );

    }
}
