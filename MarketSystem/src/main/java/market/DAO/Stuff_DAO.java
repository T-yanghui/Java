package market.DAO;

import market.moudle.Stuff;
import market.util.DBClose;
import market.util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Stuff_DAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //根据ID返回查询
    public  ArrayList<Stuff> ID_query(int ID){
        ArrayList<Stuff> stuffs = new ArrayList<Stuff>();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "select name,password,priority from Stuff where SID =?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String pwd = rs.getString("password");
                int pri = rs.getInt("priority");
                Stuff stuff = new Stuff(ID,name,pwd,pri);
                stuffs.add(stuff);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBClose.queryclose(ps,rs,conn);
        }
        return stuffs;
    }

    //根据姓名返回查询;
    public  ArrayList<Stuff> name_query(String name){
        ArrayList<Stuff> stuffs = new ArrayList<Stuff>();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "select SID,name,password,priority from Stuff where name =?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("SID");
                String pwd = rs.getString("password");
                int priority = rs.getInt("priority");
                Stuff stuff = new Stuff(id,name,pwd,priority);
                stuffs.add(stuff);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBClose.queryclose(ps,rs,conn);
        }
        return stuffs;
    }

    //添加员工
    public boolean addStuff(Stuff stuff){
        String name = stuff.getName();
        String pwd = stuff.getPassword();
        int priority = stuff.getPriority();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "insert into Stuff (name,password,priority)values(?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
            ps.setInt(3,priority);
            ps.executeUpdate();
          //  System.out.println(name);
          //  System.out.println(ps.toString());
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            DBClose.addclose(ps,conn);
        }
        return true;
    }

    //删除员工 param ID
    public boolean deleteStuff(Stuff stuff){
        int id = stuff.getID();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = " delete from Stuff where SID = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            //System.out.println(ps.toString());
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            DBClose.addclose(ps,conn);
        }
        return true;
    }
    //修改员工信息
    public boolean updatestuff(Stuff stuff){

        conn = new DBConnect().getConn(); //连接数据库
        try{
            conn.setAutoCommit(false);
            String sql_1 = " delete from Stuff where SID = ?";
            ps = conn.prepareStatement(sql_1);
            ps.setInt(1,stuff.getID());
            ps.executeUpdate();
            String sql_2 = "insert into Stuff (SID,name,password,priority)values(?,?,?,?)";
            ps = conn.prepareStatement(sql_2);
            ps.setInt(1,stuff.getID());
            ps.setString(2, stuff.getName());
            ps.setString(3, stuff.getPassword());
            ps.setInt(4, stuff.getPriority());
            ps.executeUpdate();
            //System.out.println(ps.toString());
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            }catch (SQLException e1){e1.printStackTrace();}
            return false;
        }finally{
            DBClose.addclose(ps,conn);
        }
        return true;
    }
    //返回全体员工列表
    public  ArrayList<Stuff> all_stuff_query(){
        ArrayList<Stuff> stuffs = new ArrayList<Stuff>();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "select * from Stuff order by priority";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("SID");
                String name = rs.getString("name");
                String pwd = rs.getString("password");
                int pri = rs.getInt("priority");
                Stuff stuff = new Stuff(id,name,pwd,pri);
                stuffs.add(stuff);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBClose.queryclose(ps,rs,conn);
        }
        return stuffs;
    }
}
