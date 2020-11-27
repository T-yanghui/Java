package market.page;

import market.DAO.Good_DAO;
import market.DAO.Stuff_DAO;
import market.moudle.Good;
import market.moudle.Stuff;
import market.util.*;

import java.util.ArrayList;

import static market.util.ScannerChoice.scanner_string;

public class SysControl {
    private static boolean login_flag = false; //登录成功标志
    public static void page(){
        System.out.println("--主页>>系统管理------------");
        //验证失败，退出系统
        if(login_flag == false) {
            if (LoginCheck.check(3) == null) {
                System.out.println("账户验证失败...退出系统...");
                System.exit(0);
            }
        }
        //成功
        login_flag = true;
        System.out.println("\t1.今日销售列表");
        System.out.println("\t2.员工管理");
        System.out.println("\t3.返回上级页面");
        System.out.println("\t0.退出系统");
        System.out.println("请输入数字：");
        //选择
        do{
            String choice = scanner_string();
            String reg = "[0-3]";
            if(choice.matches(reg)){
                int input = Integer.parseInt(choice);

                switch(input){
                    case 3:
                        System.out.println("-------------------------");
                        System.out.println("\t返回上级页面...");
                        MainPage.mainpage();
                        break;
                    case 2:
                        SysControl.stuffmanage();
                        break;
                    case 1:
                        //今日销售页面
                        SysControl.sales_today();
                        break;
                    case 0:
                        System.out.println("退出系统...");
                        System.exit(0);
                    default:break;
                }
            }else {
                System.err.println("输入有误！");
                System.out.println("重新输入：");
            }
        }while (true);
    }
    public static void stuffmanage(){
        System.out.println("--主页>>系统管理>>员工管理-------");
        System.out.println("\t1.查询员工");
        System.out.println("\t2.删除员工");
        System.out.println("\t3.修改员工信息");
        System.out.println("\t4.添加员工");
        System.out.println("\t5.返回上级页面");
        System.out.println("\t0.退出系统");
        System.out.println("请输入数字：");
        //选择
        do{
            String choice = scanner_string();
            String reg = "[0-5]";
            if(choice.matches(reg)){
                int input = Integer.parseInt(choice);

                switch(input){
                    case 5:
                        System.out.println("-------------------------");
                        System.out.println("\t返回上级页面...");
                        SysControl.page();
                        break;
                    case 4:
                        SysControl.addstuff();
                        break;
                    case 3:
                        SysControl.updatestuff();
                        break;
                    case 2:
                        SysControl.deletestuff();
                        break;
                    case 1:
                        //查询员工;
                        SysControl.query_stuff();
                        break;
                    case 0:
                        System.out.println("退出系统...");
                        System.exit(0);
                    default:break;
                }
            }else {
                System.err.println("输入有误！");
                System.out.println("重新输入：");
            }
        }while (true);
    }
    //添加员工
    public static void addstuff(){
        System.out.println("--主页>>系统管理>>员工管理>>添加员工----");
        do {
            System.out.print("员工姓名:");
            String name = ScannerChoice.scanner_string();
            System.out.print("设置初始密码：");
            String pwd = ScannerChoice.scanner_string();
            System.out.println("员工权限：1-售货员 2-仓库管理 3-系统管理");
            int pri = Integer.parseInt(ScannerChoice.scanner_string());
            System.out.println(pri);
            if(LoginCheck.Login_pri <= pri){
                System.out.println("权限不够...");
                continue;
            }
            Stuff stuff = new Stuff(name, pwd, pri);
            if (new Stuff_DAO().addStuff(stuff)) {
                System.out.println("添加成功...");
                //输出ID、name、pwd
                ArrayList<Stuff> stuffs =new Stuff_DAO().name_query(name);
                Stuff temp = stuffs.get(0);
                System.out.printf("员工ID:%d   姓名:%s   密码:%s\n",temp.getID(),temp.getName(),temp.getPassword());
            } else System.out.println("添加失败...");
            if(YesorNo.judge(1)) continue;
            else SysControl.stuffmanage();
        }while(true);
    }
    //输入员工ID删除
    public static void deletestuff(){
        System.out.println("--主页>>系统管理>>员工管理>>删除员工----");
        do {
            System.out.print("输入删除员工ID:");
            int id = ScannerChoice.scanner_int();
            if(id == -1) {
                System.out.println("-------返回上级页面--------------");
                SysControl.stuffmanage();
            }
            ArrayList<Stuff> stuffs =new Stuff_DAO().ID_query(id);
            if(stuffs.size() == 0){
                System.out.println("员工ID输入错误");
                continue;
            }
            Stuff temp = stuffs.get(0);
            System.out.printf("员工ID:%d   姓名:%s\n",temp.getID(),temp.getName());
            System.out.println("是否删除y/n?");
            if(YesorNo.judge(0)) {
                if (new Stuff_DAO().deleteStuff(temp))
                    System.out.println("删除成功...");
                else
                    System.out.println("删除失败...");
            }
            if(YesorNo.judge(1)) continue;
            else SysControl.stuffmanage();
        }while(true);

    }
    //修改员工密码和权限
    public static void updatestuff(){
        System.out.println("--主页>>系统管理>>员工管理>>修改员工信息----");
        do {
            System.out.print("输入员工ID:");
            //如果ID连续输错三次，即跳出当前页面
            int id = ScannerChoice.scanner_int();
            if(id == -1) {
                System.out.println("-------返回上级页面--------------");
                SysControl.stuffmanage();
            }
            ArrayList<Stuff> stuffs =new Stuff_DAO().ID_query(id);
            if(stuffs.size() == 0){
                System.out.println("员工ID输入错误");
                continue;
            }
            Stuff temp = stuffs.get(0);
            System.out.printf("员工ID:%010d   姓名:%s   权限：%s\n",temp.getID(),temp.getName(),temp.getPriority());
            System.out.println("是否修改y/n?");
            //修改权限校验
            int priority = temp.getPriority();
            if(priority >= LoginCheck.Login_pri ){
                System.out.println("权限不够...返回...");
                SysControl.stuffmanage();
            }
            if(YesorNo.judge(0)) {
                System.out.println("\t1.修改姓名");
                System.out.println("\t2.修改密码");
                System.out.println("\t3.修改权限");
            //选择
                Loop:do{
                    String choice = scanner_string();
                    String reg = "[1-3]";
                    if(choice.matches(reg)){
                        int input = Integer.parseInt(choice);

                        switch(input){
                            case 1:
                                System.out.println("重设员工姓名：");
                                temp.setName(ScannerChoice.scanner_string());
                                break Loop;
                            case 2:
                                System.out.println("重设员工密码：");
                                temp.setPassword(ScannerChoice.scanner_string());
                                break Loop;
                            case 3:
                                //修改
                                System.out.println("重设员工权限 1-售货员 2-仓库管理 3-系统管理：");
                                temp.setPriority(priority);
                                break Loop;
                            default:break;
                        }

                    }else {
                        System.err.println("输入有误！");
                        System.out.println("重新输入：");
                    }
                }while (true);
                //修改信息
                if (new Stuff_DAO().updatestuff(temp))
                    System.out.println("修改成功...");
                else
                    System.out.println("修改失败...");
            }
            //是否继续
            if(YesorNo.judge(1)) continue;
            else SysControl.stuffmanage();
        }while(true);
    }
    //模糊查询员工
    public static void query_stuff() {
        System.out.println("--主页>>系统管理>>员工管理>>查询员工----");
        do {
            System.out.println("\t1.员工列表");
            System.out.println("\t2.姓名查询员工");
            System.out.println("\t3.ID查询员工");
            System.out.println("\t4.返回上级页面");
            System.out.println("\t0.退出系统");
            System.out.println("请输入数字：");
            //选择
            Loop:do {
                String choice = scanner_string();
                String reg = "[0-4]";
                if (choice.matches(reg)) {
                    int input = Integer.parseInt(choice);

                    switch (input) {
                        case 4:
                            System.out.println("-------------------------");
                            System.out.println("\t返回上级页面...");
                            SysControl.stuffmanage();
                            break Loop;
                        case 3:
                            System.out.println("输入员工ID:");
                            if(!Stuff_Display.Stuff_ID(ScannerChoice.scanner_int())) {
                                System.out.println("查无此人...返回上级...");
                                SysControl.stuffmanage();
                            }
                            break Loop;
                        case 2:
                            System.out.println("输入员工姓名:");
                            //姓名查询员工
                            if(!Stuff_Display.Stuff_name(ScannerChoice.scanner_string())) {
                                System.out.println("查无此人...返回上级...");
                                SysControl.stuffmanage();
                            }
                            break Loop;
                        case 1:
                            //全体员工列表
                            if(!Stuff_Display.all_Stuff()) {
                                System.out.println("查无此人...返回上级...");
                                SysControl.stuffmanage();
                            }
                            break Loop;
                        case 0:
                            System.out.println("退出系统...");
                            System.exit(0);
                        default:
                            break Loop;
                    }
                } else {
                    System.err.println("输入有误！");
                    System.out.println("重新输入：");
                }
            } while (true);
            //是否继续
            if(YesorNo.judge(1)) continue;
            else SysControl.stuffmanage();
        }while(true);
    }
    //今日销售
    public static void sales_today(){
        ArrayList<Good> goods = new Good_DAO().all_sales();
        Good_sale_Display.display(goods);
        System.out.println("输入1，返回上级");
        System.out.println("输入0，退出系统");
        //选择
        do{
            String choice = scanner_string();
            String reg = "[0-1]";
            if(choice.matches(reg)){
                int input = Integer.parseInt(choice);

                switch(input){
                    case 1:
                        System.out.println("-------------------------");
                        System.out.println("\t返回上级页面...");
                        SysControl.page();
                        break;
                    case 0:
                        System.out.println("退出系统...");
                        System.exit(0);
                    default:break;
                }
            }else {
                System.err.println("输入有误！");
                System.out.println("重新输入：");
            }
        }while (true);
    }
}

