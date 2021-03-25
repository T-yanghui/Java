package com.example.web1.Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils {
    private static ComboPooledDataSource comboPooledDataSource = null;
    static {
        comboPooledDataSource = new ComboPooledDataSource("Raspberry");
    }

    public static DataSource getDataSource(){
        return comboPooledDataSource;
    }
    public static Connection getConnection(){
        try{
            return comboPooledDataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("JDBCUtils--getConnection失败！");
        }
    }
}
