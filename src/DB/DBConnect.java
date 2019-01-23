package DB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {
    //构造方法私有化
    private DBConnect() { }

    private static Connection dbConnection;

    public Connection getDBConection() {
        if (dbConnection == null) {
            getConnection();//如果对象为空,获取数据库连接
        }
        try {
            if (dbConnection.isClosed())//如果数据连接已关闭  重新进行连接
                getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("数据库连接失败");
        }
        return dbConnection;
    }

    //单例模式用于避免重复连接数据库
    public static DBConnect getInstance() {
        return DBConnectHelp.dbCon;
    }

    static class DBConnectHelp {
        public final static DBConnect dbCon = new DBConnect();
    }

    private void getConnection() {
        String driver = null;
        String url = null;
        String user = null;
        String psd = null;
        Connection conn = null;
        Properties pro = new Properties();    //新建一个properties实例，用于从DBConfig中拿到连接参数。
        try {
            pro.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties")); //加载DBConfig文件。
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            psd = pro.getProperty("psd");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("配置文件读取失败");
        }

        try {
            Class.forName(driver);   //加载jdbc驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.print("加载jdbc驱动失败");
        }

        try {
            dbConnection = DriverManager.getConnection(url, user, psd);  //获取数据库连接
            dbConnection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("数据库连接失败");
        }
    }

}