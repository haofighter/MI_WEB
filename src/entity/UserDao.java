package entity;

import DB.DBConnect;
import DB.DBmanage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

    /**
     * 通过名字得到类
     *
     * @param name
     * @return
     */
    public User checkUserByName(String name) {//按照名字查询基本与id查询一样，sql语句不同
        String sql = "select * from user where user ='" + name + "'";
        Connection con = DBConnect.getInstance().getDBConection();
        Statement st = null;
        ResultSet rs = null;
        User user = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String useName = rs.getString("user");
                String psd = rs.getString("psd");
                user = new User(useName, psd);
                System.out.println("数据库查询到的数据" + user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public boolean add(User user) {//添加
        String sql = "insert into user(user, psd) values('" + user.getUserName() + "','" + user.getPassword() + "')";
        Connection con = DBConnect.getInstance().getDBConection();
        try {
            Statement st = con.createStatement();
            return st.executeUpdate(sql) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("添加数据失败" + e.getMessage());
        } finally {
            try {
                con.commit();//必须进行提交  否则数据库不会插入数据
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.print("配置文件读取失败");
            }
        }
        return false;
    }
}
