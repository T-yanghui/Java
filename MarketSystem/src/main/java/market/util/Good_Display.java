package market.util;

import market.DAO.Good_DAO;
import market.moudle.Good;

import java.util.ArrayList;

/**
 * @ClassName Good_Display
 * @Description TODO
 * @Author Yanghui
 * @Date 2020/11/25 22:29
 * @Version 1.0
 **/
public class Good_Display {
    //全体员工列表
    public static boolean all_Good(){
        ArrayList<Good> goods = new Good_DAO().all_good_query();
        if(goods != null || goods.size()!=0){
            System.out.println("----------------商品列表---------------");
            for( Good good:goods)
                System.out.printf("商品ID:%010d  商品名称：%s  商品价格：%.2f  商品数量%.0f\n", good.getGID(),good.getGname(),good.getPrice(),good.getGnum());
            return true;
        }
        return false;
    }

     public static boolean Good_name(String Gname){
        ArrayList<Good> goods = new Good_DAO().Gname_query(Gname);
        if(goods != null || goods.size()!=0){
            System.out.println("----------------商品列表---------------");
            for( Good good:goods)
                System.out.printf("商品ID:%010d  商品名称：%s  商品价格：%.2f  商品数量%.0f\n", good.getGID(),good.getGname(),good.getPrice(),good.getGnum());
            return true;
        }
        return false;
     }

      public static boolean Good_ID(int id){
        ArrayList<Good> goods = new Good_DAO().GID_query(id);
        if(!goods.isEmpty()){
            System.out.println("----------------商品列表---------------");
            for(Good good:goods)
                 System.out.printf("商品ID:%010d  商品名称：%s  商品价格：%.2f  商品数量%.0f\n", good.getGID(),good.getGname(),good.getPrice(),good.getGnum());
            return true;
        }
        return false;
    }
}
