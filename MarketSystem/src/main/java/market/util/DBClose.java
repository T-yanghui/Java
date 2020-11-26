package market.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//考虑两种情况的关闭
public final class  DBClose {
    //关闭添加Stuff的连接
    public static void addclose(PreparedStatement ps, Connection conn){
        try{
            if(ps!=null) ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null) conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //关闭查询连接
    public static void queryclose(PreparedStatement ps, ResultSet rs,Connection conn){
        try{
            if(rs!=null) rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            if(ps!=null) ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null) conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
