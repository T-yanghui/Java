package market.util;

import market.DAO.Stuff_DAO;
import market.moudle.Stuff;

import java.util.ArrayList;

public class Stuff_Display {
    public static boolean Stuff_ID(int id){
        ArrayList<Stuff> stuffs = new Stuff_DAO().ID_query(id);
        if(stuffs != null || stuffs.size()!=0){
            System.out.println("----------------员工列表---------------");
            for(Stuff stuff:stuffs)
                System.out.printf("ID:%d  姓名：%s  权限：%d\n",stuff.getID(),stuff.getName(),stuff.getPriority());
            return true;
        }
        return false;
    }
    public static boolean Stuff_name(String name){
        ArrayList<Stuff> stuffs = new Stuff_DAO().name_query(name);
        if(stuffs != null || stuffs.size()!=0){
            System.out.println("----------------员工列表---------------");
            for(Stuff stuff:stuffs)
                System.out.printf("ID:%d  姓名：%s  权限：%d\n",stuff.getID(),stuff.getName(),stuff.getPriority());
            return true;
        }
        return false;
    }
    //全体员工列表
    public static boolean all_Stuff(){
        ArrayList<Stuff> stuffs = new Stuff_DAO().all_stuff_query();
        if(stuffs != null || stuffs.size()!=0){
            System.out.println("----------------员工列表---------------");
            for(Stuff stuff:stuffs)
                System.out.printf("ID:%d  姓名：%s  权限：%d\n",stuff.getID(),stuff.getName(),stuff.getPriority());
            return true;
        }
        return false;
    }
}
