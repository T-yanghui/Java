package market.util;

import market.moudle.Good;
import market.page.SysControl;

import java.util.ArrayList;

/**
 * @ClassName Good_Display
 * @Description 商品展示
 * @Author Yanghui
 * @Date 2020/11/24 10:07
 * @Version 1.0
 **/
public class Good_sale_Display {
    public static void display(ArrayList<Good> goods ){
        if(goods.isEmpty()) {
            System.out.println("无商品...");
            return;
        }
        System.out.println("商品ID       商品名称   销量");
        for(Good good:goods){
            System.out.printf("%d    %s   %.0f\n",good.getGID(),good.getGname(),good.getSales());
        }
    }
}
