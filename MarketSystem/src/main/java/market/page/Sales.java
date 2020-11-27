package market.page;

import market.DAO.Good_DAO;
import market.moudle.Good;
import market.moudle.Stuff;
import market.util.Good_Display;
import market.util.LoginCheck;
import market.util.ScannerChoice;
import market.util.YesorNo;

import static market.util.ScannerChoice.scanner_string;

/**
 * @ClassName Sales
 * @Description 前台收银
 * @Author Yanghui
 * @Date 2020/11/24 21:12
 * @Version 1.0
 **/

public class Sales {
    private static boolean login_flag = false; //登录成功标志

/*
 * @Description //TODO
 * @Date 21:28 2020/11/24
 * @Param []
 * @return void
 **/
    public static void page(){
         System.out.println("--主页>>前台收银------------");
         //Stuff保存用户信息.传递给调用的方法;
         Stuff stuff = null;
        //验证失败，退出系统
        if (login_flag == false) {
            stuff = LoginCheck.check(1);
            if (stuff == null) {
                System.out.println("账户验证失败...退出系统...");
                System.exit(0);
            }
        }
        //成功
        login_flag = true;
        System.out.println("\t1.结账页面");
        System.out.println("\t2.返回上级页面");
        System.out.println("\t0.退出系统");
        System.out.println("请输入数字：");
        //选择
        do {
            String choice = scanner_string();
            String reg = "[0-2]";
            if (choice.matches(reg)) {
                int input = Integer.parseInt(choice);

                switch (input) {
                    case 2:
                        System.out.println("-------------------------");
                        System.out.println("\t返回上级页面...");
                        MainPage.mainpage();
                        break;
                    case 1:
                        //结账页面
                        paymentPage(stuff);
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

    public static void paymentPage(Stuff stuff){
        do {
            int id;//商品ID
            double num;//购买的商品数量
            Good good = null;
            System.out.println("--主页>>前台收银>>结账页面------------");
            System.out.print("输入商品ID(0返回上级):");
            id = ScannerChoice.scanner_int();
            if(id == 0) page();
            good = Good_Display.Good_ID(id).get(0);
            if(good == null) continue;
            System.out.println("请输入购买数量：");
            num = ScannerChoice.scanner_double();
            if(num > good.getGnum()){
                System.out.println("商品库存不足");
                continue;
            }
            //实付 应付
            double payment;
            double sum = good.getPrice()*num;
            System.out.printf("商品ID:%d  商品名称：%s  单价:￥%.2f 数量：%.0f  总价￥%.2f\n",
                    good.getGID(),good.getGname(),good.getPrice(),num,good.getPrice()*num);
            System.out.print("实收：￥");
            payment = ScannerChoice.scanner_double();
            if(payment < sum ){
                System.out.println("金额不足...");
                continue;
            }
            System.out.printf("找零：￥%.0f\n",payment - sum);
            good.setGnum(good.getGnum()-num);
            if(!new Good_DAO().insert_Goods_log_Goods(good,stuff,num)){
                System.out.println("支付失败,请重试...");
                continue;
            }
            if(YesorNo.judge(1)) continue;
            else page();
        }while (true);
    }
}
