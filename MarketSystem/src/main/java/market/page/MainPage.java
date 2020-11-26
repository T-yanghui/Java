package market.page;

import market.util.ScannerChoice;

/*主页面;
    程序入口;
 */
public class MainPage extends ScannerChoice {
    //入口函数
    public static void main(String[] argus){
            MainPage.mainpage();
    }
    public static void mainpage(){
        System.out.println("--主页---------------------------");
        System.out.println("\t1.系统管理");
        System.out.println("\t2.售货员");
        System.out.println("\t3.商品管理");
        System.out.println("*******----------------**********");

        System.out.println("请输入数字(输入0退出系统)：");
        do{
            String choice = scanner_string();
            String reg = "[0-3]";
            if(choice.matches(reg)){
                int input = Integer.parseInt(choice);

                switch(input){
                    case 0:
                        System.out.println("-------------------------");
                        System.out.println("\t退出系统...");
                        System.exit(0);//退出系统
                        break;
                    case 1:
                        SysControl.page();
                        break;
                    case 2:
                        Sales.page();
                        break;
                    case 3:
                        CommodityManage.page();
                        break;
                    default:break;
                }
            }else {
                System.err.println("输入有误！");
                System.out.println("重新输入或输入0退出：");
            }
        }while (true);
    }
}
