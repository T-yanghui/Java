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
}
