package ru.otus.java.pro.dbinteraction;

import java.sql.SQLException;

public class AuthenticationService {
    private UsersDao usersDao;

    public AuthenticationService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void register(String login, String password, String nickname) throws SQLException {
        usersDao.save(new User(null, login, password, nickname));
    }
}
