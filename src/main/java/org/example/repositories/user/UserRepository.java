package org.example.repositories.user;

import org.example.app.Authentication;
import org.example.model.Vehicle;
import org.example.repositories.vehicle.VehicleRepository;

import java.io.*;
import java.util.ArrayList;

public class UserRepository implements IUserRepository
{

    private ArrayList<User> users;

    public UserRepository(String filePath)
    {
        this.users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            reader.readLine(); // slip 1st line
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split("; ");
                String login = parts[0];
                String passwordHash = parts[1];
                String role = parts[2];
                int rentedVehicleID = Integer.parseInt(parts[3]);

                User user = new User(login, passwordHash, role, rentedVehicleID, true);
                users.add(user);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    @Override
    public User getUser(String login)
    {
        return users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<User> getUsers()
    {
        return users;
    }

    @Override
    public void save(User user, String path)
    {
        if (getUser(user.getLogin()) != null)
        {
            System.err.println("Użytkownik o loginie " + user.getLogin() + " już istnieje w systemie.");
            return;
        }
        users.add(user);

        try
        {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter out = new PrintWriter(bufferedWriter);
            out.println(user.toCSV());
            out.flush();
        }
        catch (IOException ex)
        {
            throw new RuntimeException("Błąd podczas zapisu do pliku: " + path, ex);
        }
    }

    public void printAllUsers(User user)
    {
        if (!Authentication.isAdmin(user.getLogin())) System.err.println("Nie jesteś adminem, więc nie możesz otrzymać listy użytkowników!");
        else
        {
            System.out.println("Lista użytkowników:");
            for (User u : users)
            {
                System.out.println(u.toString());
                if (user.getRentedVehicleID() != -1) System.out.println(user.getRentedVehicleID());
                else System.out.println("Brak wypożyczonego pojezu");
            }
        }
    }

    public void printUserDetails(User user)
    {
        if (user != null)
        {
            System.out.println(user.toString());
            if (user.getRentedVehicleID() != -1) System.out.println(user.getRentedVehicleID());
            else System.out.println("No vehicle rented");
        }
        else
        {
            System.out.println("Nie znaleziono użytkownika o loginie: " + user.getLogin());
        }
    }

    public void addVehicle(User user, VehicleRepository vehicleRepository, Vehicle vehicle)
    {
        if (Authentication.isAdmin(user.getLogin())) vehicleRepository.addVehicle(vehicle);
        else System.err.println("Nie jesteś adminem, więc nie możesz dodać pojazdu!");
    }

    public void removeVehicle(User user, VehicleRepository vehicleRepository, int ID)
    {
        if (Authentication.isAdmin(user.getLogin())) vehicleRepository.removeVehicle(ID);
        else System.err.println("Nie jesteś adminem, więc nie możesz usunąć pojazdu!");
    }

}
