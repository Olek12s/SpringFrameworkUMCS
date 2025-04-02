package org.example.repositories.user;

import org.apache.commons.codec.digest.DigestUtils;

public class User
{
    private String login;
    private transient String passwordHash;
    private String role;
    private int rentedVehicleID;

    public User(String login, String password, String role, int rentedVehicleID, boolean passwordHashed)
    {
        this.login = login;
        if(!passwordHashed) this.passwordHash = DigestUtils.sha256Hex(password);
        else this.passwordHash = password;
        this.role = role;
        this.rentedVehicleID = rentedVehicleID;
    }
    public User(String login, String password, String role, int rentedVehicleID)
    {
        this(login, password, role, rentedVehicleID, false);
    }

    public String getLogin() {return login;}
    public String getPasswordHash() {return passwordHash;}
    public String getRole() {return role;}
    public int getRentedVehicleID() {return rentedVehicleID;}

    public String toCSV()
    {
        return login + "; " + passwordHash + "; " + role + "; " + rentedVehicleID;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", role='" + role + '\'' +
                ", rentedVehicle=" + rentedVehicleID +
                '}';
    }
}
