package org.example.app;

import org.apache.commons.codec.digest.DigestUtils;
import org.example.repositories.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Authentication
{
    private UserRepository userRepository;
    private static ArrayList<String> adminLogins = new ArrayList<>(List.of("Admin000", "Admin1", "Admin2"));

    public Authentication(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public boolean assertCorrectness(String login, String password)
    {
        if (!validateLogin(login)) return false;
        if (!validatePassword(password)) return false;
        return true;
    }

    private boolean validateLogin(String login)
    {
        return userRepository.getUsers().stream()
                .filter(u -> u.getLogin().equals(login))
                .anyMatch(u -> u.getLogin().equals(login));
    }

    private boolean validatePassword(String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        return userRepository.getUsers().stream()
                .anyMatch(u -> u.getPasswordHash().equals(hashedPassword));
    }

    public void login()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj login: ");
        String login = scanner.nextLine();

        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        if (assertCorrectness(login, password))
        {
            if (isAdmin(login)) System.out.println("\u001B[32mZalogowano pomyślnie jako admin!\u001B[0m");
            else System.out.println("\u001B[32mZalogowano pomyślnie!\u001B[0m");
        }
        else
        {
            System.out.println("\u001B[31mBłędny login lub hasło!\u001B[0m");
        }
    }

    public static boolean isAdmin(String login)
    {
        return adminLogins.contains(login);
    }
}
