package market.util;

import market.page.SysControl;
//0自定义输出 1.直接输出
public class YesorNo {
    public static boolean judge(int flag) {
        if(flag == 1)System.out.println("是否继续y/n:");
        String temp = ScannerChoice.scanner_string();
        if (temp.equals("Y") || temp.equals("y")) return true;
        else return false;
    }
}
