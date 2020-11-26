package market.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnect {
    private static final String url  = "jdbc:mysql://10.28.212.34:3306/Market?useUnicode=true&characterEncoding=utf8";
    private static final String usr = "root";
    private static final String PASSWORD = "123456";
    private Connection conn;
    //如果conn为static，初次创建后即使关闭，也不会重新创建连接
    //加载驱动程序、获取数据库连接
    public  Connection getConn() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,usr,PASSWORD);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

}
