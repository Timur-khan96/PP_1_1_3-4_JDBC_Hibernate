package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Создание таблицы User(ов)
        // Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль (  )
        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        // Очистка таблицы User(ов)
        // Удаление таблицы

        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        for (int i = 0; i < 4; i++) service.saveUser("randomName", "randomLastName", (byte) 18);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();

        Util.closeConnection();

    }
}
