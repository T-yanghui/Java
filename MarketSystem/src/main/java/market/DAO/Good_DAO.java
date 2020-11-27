package market.DAO;

import market.moudle.Good;
import market.moudle.Stuff;
import market.util.DBClose;
import market.util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @ClassName Good_DAO
 * @Description 商品查询
 * @Author Yanghui
 * @Date 2020/11/24 10:21
 * @Version 1.0
 **/
public class Good_DAO {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

/*
 * @Description //查询今日销售列表
 * @Date 17:27 2020/11/25
 * @Param []
 * @return java.util.ArrayList<market.moudle.Good>
 **/
    public ArrayList<Good> all_sales() {
        ArrayList<Good> goods = new ArrayList<Good>();
        Connection conn = new DBConnect().getConn();
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        String sql = "select * from Goods_log where Date = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDate(1,date);
            rs = ps.executeQuery();
            while (rs.next()) {
                int GID = rs.getInt("GID");
                String Gname = rs.getString("Gname");
                double sale = rs.getDouble("sale");
                Good good = new Good(GID,Gname,sale);
                goods.add(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.queryclose(ps, rs, conn);
        }
        return goods;

    }
    /*
     * @Description //删除商品
     * @Date 17:29 2020/11/25
     * @Param [good]
     * @return boolean
     **/
    public boolean deleteCommodity(Good good){
            int Gid = good.getGID();
            conn = new DBConnect().getConn(); //连接数据库
            String sql = " delete from Goods where GID = ?";
            try{
                ps = conn.prepareStatement(sql);
                ps.setInt(1,Gid);
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
/*
 * @Description //修改商品信息
 * @Date 19:16 2020/11/25
 * @Param [good]
 * @return boolean
 **/
    public boolean updateGood(Good good){
        conn = new DBConnect().getConn(); //连接数据库
        try{
            conn.setAutoCommit(false);
            String sql_1 = " delete from Goods where GID = ?";
            ps = conn.prepareStatement(sql_1);
            ps.setInt(1,good.getGID());
            ps.executeUpdate();
            String sql_2 = "insert into Goods (GID,Gname,Gprice,Gnum)values(?,?,?,?)";
            ps = conn.prepareStatement(sql_2);
            ps.setInt(1,good.getGID());
            ps.setString(2, good.getGname());
            ps.setDouble(3, good.getPrice());
            ps.setDouble(4, good.getGnum());
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
    /*
     * @Description //货物ID查询
     * @Date 19:45 2020/11/25
     * @Param [ID]
     * @return java.util.ArrayList<market.moudle.Good>
     **/
    public  ArrayList<Good> GID_query(int ID){
        ArrayList<Good> goods = new ArrayList<Good>();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "select Gname,Gprice,Gnum from Goods where GID =?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
            rs = ps.executeQuery();
            while(rs.next()){
                String Gname = rs.getString("Gname");
                double Gprice = rs.getDouble("Gprice");
                double Gnum  = rs.getDouble("Gnum");
                Good good = new Good(ID,Gname,Gprice,Gnum);
                goods.add(good);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBClose.queryclose(ps,rs,conn);
        }
        return goods;
    }
    //添加商品
    public boolean addGood(Good good){
        int GID = good.getGID();
        String Gname = good.getGname();
        double Gprice = good.getPrice();
        double Gnum = good.getGnum();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "insert into Goods(GID,Gname,Gprice,Gnum)values(?,?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,GID);
            ps.setString(2,Gname);
            ps.setDouble(3,Gprice);
            ps.setDouble(4,Gnum);
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

    //根据商品名称返回查询;
    public  ArrayList<Good> Gname_query(String name){
        ArrayList<Good> goods = new ArrayList<Good>();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "select GID,Gname,Gprice,Gnum from Goods where Gname REGEXP ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            while(rs.next()){
                int GID = rs.getInt("GID");
                String Gname = rs.getString("Gname");
                double Gprice = rs.getDouble("Gprice");
                double Gnum = rs.getDouble("Gnum");
                goods.add(new Good(GID,Gname,Gprice,Gnum));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBClose.queryclose(ps,rs,conn);
        }
        return goods;
    }
    //返回所有商品
     public  ArrayList<Good> all_good_query(){
        ArrayList<Good> goods = new ArrayList<Good>();
        conn = new DBConnect().getConn(); //连接数据库
        String sql = "select * from Goods order by Gnum";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int GID = rs.getInt("GID");
                String Gname = rs.getString("Gname");
                double Gprice = rs.getDouble("Gprice");
                double Gnum = rs.getDouble("Gnum");
                goods.add(new Good(GID,Gname,Gprice,Gnum));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBClose.queryclose(ps,rs,conn);
        }
        return goods;
    }
    /*
     * @Description //插入Goods_log销售信息
     * @Date 0:08 2020/11/28
     * @Param
     * @return boolean
     **/
    public boolean insert_Goods_log_Goods(Good good,Stuff stuff,double num){
        conn = new DBConnect().getConn(); //连接数据库
        try{
        //修改商品信息;
            conn.setAutoCommit(false);
            String sql_1 = " delete from Goods where GID = ?";
            ps = conn.prepareStatement(sql_1);
            ps.setInt(1,good.getGID());
            ps.executeUpdate();
            String sql_2 = "insert into Goods (GID,Gname,Gprice,Gnum)values(?,?,?,?)";
            ps = conn.prepareStatement(sql_2);
            ps.setInt(1,good.getGID());
            ps.setString(2, good.getGname());
            ps.setDouble(3, good.getPrice());
            ps.setDouble(4, good.getGnum());
            ps.executeUpdate();
            //System.out.println(ps.toString());
        //插入表Goods_log信息
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            String sql_3 = "insert into Goods_log (SID,GID,Gname,sale,date) value(?,?,?,?,?)";
            ps = conn.prepareStatement(sql_3);
            ps.setInt(1,stuff.getID());
            ps.setInt(2,good.getGID());
            ps.setString(3,good.getGname());
            ps.setDouble(4,num);
            ps.setDate(5,date);
            ps.executeUpdate();
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
}

