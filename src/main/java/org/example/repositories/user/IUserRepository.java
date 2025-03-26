package org.example.repositories.user;

import java.util.ArrayList;

public interface IUserRepository
{
    User getUser(String login);
    ArrayList<User> getUsers();
    void save(User user, String path);
}
