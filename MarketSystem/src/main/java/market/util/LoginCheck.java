package market.util;

import market.DAO.Stuff_DAO;
import market.page.MainPage;
import market.moudle.Stuff;

import java.util.ArrayList;

//检查账号和密码是否正确;
public final class LoginCheck {
    public static int Login_pri = -1;
    public static boolean check(int priority) {
        //获取输入的ID号
        int ID = 0;
        int num = 3;
        do {
            if (num-- == 0) return false;
            System.out.print("用户ID：");
            String input_1 = ScannerChoice.scanner_string();
            String reg = "[0-9]{10}";
            System.out.print("密码：");
            String input_2 = ScannerChoice.scanner_string();
            //System.out.println(input_1+input_2);
            if (input_1.matches(reg)) {
                //用ID查询数据库，返回查询结果-Stuffs
                int id = Integer.parseInt(input_1);
                ArrayList<Stuff> stuffs =new Stuff_DAO().ID_query(id);
                if(stuffs != null){
                    Stuff stuff = stuffs.get(0);
                    String pw = stuff.getPassword();
                    //检查用户权限,如果用户权限大于priority即允许登入
                    Login_pri = stuff.getPriority();
                    if(pw.equals(input_2)&&stuff.getPriority() >= priority) break;
                }
            }
            System.err.println("用户ID或者密码错误");
            String output = null;
            if (num != 0) System.out.printf("剩余%d次机会 请输入：\n", num);
        } while (true);
        System.out.println("------------------------------------------");
        return true;
    }
}