package ru.otus.java.pro.dbinteraction;

public class UsersStatisticService {
    private final UsersDao usersDao;

    public UsersStatisticService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void businessLogicMethod(Long id) {
        User user = usersDao.getUserById(id).get();
        // ... какая-то обработка данных по юзеру
        System.out.println(user);
    }
}
