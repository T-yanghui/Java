package market.page;

import market.DAO.Good_DAO;
import market.DAO.Stuff_DAO;
import market.moudle.Good;
import market.moudle.Stuff;
import market.util.Good_Display;
import market.util.LoginCheck;
import market.util.ScannerChoice;
import market.util.YesorNo;

import java.util.ArrayList;

import static market.util.ScannerChoice.scanner_double;
import static market.util.ScannerChoice.scanner_string;

public class CommodityManage {
    private static boolean login_flag = false; //登录成功标志

    public static void page() {
        System.out.println("--主页>>商品管理------------");
        //验证失败，退出系统
        if (login_flag == false) {
            if (!LoginCheck.check(2)) {
                System.out.println("账户验证失败...退出系统...");
                System.exit(0);
            }
        }
        //成功
        login_flag = true;
        System.out.println("\t1.查询商品");
        System.out.println("\t2.删除商品");
        System.out.println("\t3.添加商品");
        System.out.println("\t4.修改商品");
        System.out.println("\t5.返回上级");
        System.out.println("\t0.退出系统");
        System.out.println("请输入数字：");
        //选择
        do {
            String choice = scanner_string();
            String reg = "[0-5]";
            if (choice.matches(reg)) {
                int input = Integer.parseInt(choice);

                switch (input) {
                    case 5:
                        System.out.println("-------------------------");
                        System.out.println("\t返回上级页面...");
                        MainPage.mainpage();
                        break;
                    case 4:
                        //修改商品
                        CommodityManage.updateCommodity();
                        break;
                    case 3:
                        //添加商品
                        addCommodity();

                        break;
                    case 2:
                        //删除商品
                        deleteCommodity();
                        break;
                    case 1:
                        //查询商品
                        queryGood();
                         break;
                    case 0:
                        System.out.println("退出系统...");
                        System.exit(0);
                    default:
                        break;
                }
            } else {
                System.err.println("输入有误！");
                System.out.println("重新输入：");
            }
        } while (true);
    }

    //修改商品信息
    public static void updateCommodity() {
        System.out.println("--主页>>商品管理>>修改商品信息----");
        do {
            System.out.print("输入商品ID:");
            //如果ID连续输错三次，即跳出当前页面
            int id = ScannerChoice.scanner_int();
            if (id == -1) {
                System.out.println("-------返回上级页面--------------");
                page();
            }
            ArrayList<Good> goods = new Good_DAO().GID_query(id);
            if (goods.size() == 0) {
                System.out.println("商品ID输入错误");
                continue;
            }
            Good good = goods.get(0);
            System.out.printf("商品ID:%010d   商品名称:%s   商品价格：%.2f  商品数量%.0f \n", good.getGID(), good.getGname(), good.getPrice(), good.getGnum());
            System.out.println("是否修改y/n?");
            if (YesorNo.judge(0)) {
                System.out.println("1.重设商品名称");
                System.out.println("2.重设商品价格");
                System.out.println("3.重设商品数量");
                System.out.println("0.退出修改");
                //选择
                Loop:
                do {
                    String choice = scanner_string();
                    String reg = "[0-3]";
                    if (choice.matches(reg)) {
                        int input = Integer.parseInt(choice);

                        switch (input) {
                            case 0:
                                CommodityManage.page();
                                break Loop;
                            case 1:
                                System.out.println("商品名称：");
                                good.setGname(ScannerChoice.scanner_string());
                                break Loop;
                            case 2:
                                System.out.println("商品价格：");
                                good.setPrice(ScannerChoice.scanner_double());
                                break Loop;
                            case 3:
                                System.out.println("商品数量：");
                                good.setGnum(ScannerChoice.scanner_double());
                                break Loop;
                            default:
                                break Loop;
                        }

                    } else {
                        System.err.println("输入有误！");
                        System.out.println("重新输入：");
                    }
                } while (true);
                //修改信息
                if (new Good_DAO().updateGood(good))
                    System.out.println("修改成功...");
                else
                    System.out.println("修改失败...");
            }
            //是否继续
            if (YesorNo.judge(1)) continue;
            else SysControl.stuffmanage();
        } while (true);
    }
    /*
     * @Description //添加商品
     * @Date 20:55 2020/11/25
     * @Param []
     * @return void
     **/
    public static void addCommodity(){
        System.out.println("--主页>>商品管理>>添加商品----");
        do {
            System.out.print("商品ID:");
            int GID = ScannerChoice.scanner_int();
            System.out.print("商品名称:");
            String Gname = scanner_string();
            System.out.print("商品价格：");
            double Gprice = scanner_double();
            System.out.print("商品数量：");
            double Gnum = scanner_double();
            Good good = new Good(GID,Gname,Gprice,Gnum);
            if (new Good_DAO().addGood(good) ){
                System.out.println("添加成功...");
                //输出ID、name、pwd
                ArrayList<Good> goods =new Good_DAO().GID_query(GID);
                Good temp = goods.get(0);
                System.out.printf("商品ID:%010d   商品名称:%s   商品价格:%.0f  商品数量：%.0f\n",temp.getGID(),temp.getGname(),temp.getPrice(),temp.getGnum());
            } else System.out.println("添加失败...");
            if(YesorNo.judge(1)) continue;
            else page();
        }while(true);
    }
    public static void deleteCommodity(){
        System.out.println("--主页>>商品管理>>删除商品----");
        do {
            System.out.print("输入删除商品ID:");
            int id = ScannerChoice.scanner_int();
            if(id == -1) {
                System.out.println("-------返回上级页面--------------");
                page();
            }
            ArrayList<Good> goods =new Good_DAO().GID_query(id);
            if(goods.size() == 0){
                System.out.println("商品ID输入错误");
                continue;
            }
            Good temp = goods.get(0);
            System.out.printf("商品ID：%010d  商品名称：%s  商品价格：%.2f 商品数量：%.0f",temp.getGID(),temp.getGname(),temp.getPrice(),temp.getGnum());
            System.out.println("是否删除y/n?");
            if(YesorNo.judge(0)) {
                if (new Good_DAO().deleteCommodity(temp))
                    System.out.println("删除成功...");
                else
                    System.out.println("删除失败...");
            }
            if(YesorNo.judge(1)) continue;
            else page();
        }while(true);
    }
     //模糊查询商品
    public static void queryGood() {
        System.out.println("--主页>>商品管理>>查询商品----");
        do {
            System.out.println("\t1.商品列表");
            System.out.println("\t2.商品名称查询");
            System.out.println("\t3.商品ID查询");
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
                            page();
                            break Loop;
                        case 3:
                            System.out.println("输入商品ID:");
                            if(!Good_Display.Good_ID(ScannerChoice.scanner_int())) {
                                System.out.println("无此商品...返回上级...");
                                page();
                            }
                            break Loop;
                        case 2:
                            System.out.println("输入商品名称:");
                            if(!Good_Display.Good_name(ScannerChoice.scanner_string())) {
                                System.out.println("无此商品...返回上级...");
                                page();
                            }
                            break Loop;
                        case 1:
                            if(!Good_Display.all_Good()) {
                                System.out.println("无此商品...返回上级...");
                                page();
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
            else queryGood();
        }while(true);
    }
}