package DB;

import entity.User;
import entity.UserDao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBmanage {
    public static User checkUser() {
        return new UserDao().checkUserByName("123");
    }

    public static boolean add(User user) {
        return new UserDao().add(user);
    }

}
