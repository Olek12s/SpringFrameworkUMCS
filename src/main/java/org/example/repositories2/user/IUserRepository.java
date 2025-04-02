package org.example.repositories2.user;

import java.util.ArrayList;

public interface IUserRepository
{
    User getUser(String login);
    ArrayList<User> getUsers();
    void save(User user, String path);
}
